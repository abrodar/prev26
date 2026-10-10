# Prev26 → RISC-V compiler

A complete compiler for **PREV'26**, a small statically typed teaching language, targeting **64-bit RISC-V (RV64IM)**. Written in Java for the *Prevajalniki* (Compilers) course at the Faculty of Computer and Information Science, University of Ljubljana (2025/26, prof. Boštjan Slivnik).

It covers the whole pipeline: from source text to assembly that runs in the [RARS](https://github.com/TheThirdOne/rars) simulator, including liveness analysis and graph-colouring register allocation with coalescing and spilling.

```
$ prev26c hanoi.p26        # compile + run (see "Running" below)
3
A>C
A>B
C>B
A>C
B>A
B>C
A>C
```

## Highlights

- **Full pipeline, no shortcuts**: lexer and parser (ANTLR4), AST, name resolution, type checking, frame layout, tree IR, canonicalisation, instruction selection, liveness, register allocation, and final emission.
- **Iterated register allocation** (George & Appel style): simplify, conservative coalescing (Briggs and George tests), freeze, optimistic spilling with highest-degree spill selection, and rewrite-and-retry. Self-moves are removed after colouring.
- **Configurable register pressure**: `--num-regs=K` limits the allocator to K general-purpose registers (1–25). This is useful for stress-testing spill code, since the same program still runs correctly with only a handful of registers.
- **Nested functions** with static links for accessing variables of enclosing functions.
- **Small runtime written in assembly**: startup code, a bump-allocator heap for `new`/`del`, and I/O routines on top of RARS system calls.
- **Every phase is inspectable**: `--target-phase` stops after any phase, and each phase can be logged to XML and rendered as HTML.

## The language in 15 lines

```
fun getint():int
fun putchar(c:char):void

fun solve(count:int, source:char, destination:char, intermediate:char):void =
    if count == 1 then
        putchar(source), putchar('>'), putchar(destination), putchar(10 as char)
    else
        solve(count - 1, source, intermediate, destination),
        solve(1, source, destination, intermediate),
        solve(count - 1, intermediate, destination, source)
    end

fun main():int = let var n:int in n = getint(), solve(n, 'A', 'C', 'B') end, 0
```

PREV'26 is expression-based: a function body is a comma-separated sequence of expressions, and the value of the last one is the result. It has `int`, `char`, `bool` and `void`, arrays (`[8]int`), pointers (`^int`), records, named types (`typ`), `if`/`while`, `let … in … end` blocks with local variables and nested functions, and explicit casts with `as`. Functions declared without a body (like `getint` above) are provided by the runtime.

## Compiler pipeline

```
source.p26
   │  lexan   ── tokens                         (ANTLR4 lexer)
   │  synan   ── parse tree                     (ANTLR4 parser)
   │  abstr   ── abstract syntax tree
   │  seman   ── names resolved, types checked
   │  memory  ── frames, offsets, static links
   │  imrgen  ── tree IR
   │  imrlin  ── canonical IR, basic blocks, traces
   │  asmgen  ── RISC-V instructions on virtual registers
   │  livean  ── liveness, interference graph
   │  regall  ── register allocation, prolog/epilog
   ▼
source.asm  → runs in RARS
```

| Phase | What it does | Code |
|---|---|---|
| `lexan` | Tokenises the source (literals, escapes, comments) | `phase/lexan` |
| `synan` | Parses with an ANTLR4 grammar and builds the AST during parsing | `phase/synan` |
| `seman` | Resolves names across scopes (including forward and mutually recursive definitions), builds types (including recursive types through pointers), type-checks every expression and reports clear errors | `phase/seman` |
| `memory` | Computes type sizes, frame layouts and variable offsets; handles static links for nested functions | `phase/memory` |
| `imrgen` | Translates the AST into a tree-based intermediate representation | `phase/imrgen` |
| `imrlin` | Canonicalises IR trees, splits them into basic blocks and schedules traces so that conditional jumps fall through to their false branch. Includes an IR interpreter for testing | `phase/imrlin` |
| `asmgen` | Instruction selection by maximal munch into RV64IM, with def/use sets and move instructions marked for coalescing | `phase/asmgen` |
| `livean` | Builds the control-flow graph, computes liveness by fixpoint iteration and builds the interference graph | `phase/livean` |
| `regall` | Colours the interference graph, coalesces moves, inserts spill code, then emits each function with its prolog and epilog | `phase/regall` |

## Runtime conventions

The compiler uses its own calling convention rather than the standard RISC-V ABI. It's simpler to generate and easy to reason about:

- **Arguments are passed on the stack**, not in `a0`–`a7`. The first slot is always the **static link**, i.e. the frame pointer of the lexically enclosing function.
- **The return value** is returned in `a0`.
- **Callee saves everything it uses**: each function saves exactly the physical registers the allocator assigned to it, so callers never need to save anything around a call.
- `s0` is the frame pointer. The allocator never hands out `zero`, `ra`, `sp`, `gp`, `tp`, `s0` or `a0`, which leaves 25 general-purpose registers.

Stack frame, from the stack pointer upwards:

```
higher addresses
        ┌────────────────────────┐ ← FP (s0)   caller's outgoing args:
        │                        │             static link at 0(FP), argument i at 8·i(FP)
        │ local variables        │
        │ return address         │
        │ old frame pointer      │
        │ spilled temporaries    │
        │ saved registers        │
        │ outgoing arguments     │  static link at 0(SP)
        └────────────────────────┘ ← SP
lower addresses
```

The runtime (emitted in front of every program) provides:

| Function | Implementation |
|---|---|
| `putint`, `putchar`, `getint`, `getchar` | RARS system calls |
| `exit` | RARS exit system call |
| `new(size)` | Bump allocator over a 64 KiB static heap |
| `del(ptr)` | No-op (the bump allocator never frees memory) |

## Building

Requirements: a JDK (the Makefile targets Java 25) and `make`. ANTLR 4.13.2 and RARS are included in the repository.

```sh
make bin
```

With an older JDK (e.g. 21), override the release level:

```sh
make bin JAVAC="javac --release 21 -encoding UTF8"
```

## Running

Compile a program to `program.asm`, then run it in RARS:

```sh
java -cp bin:src:lib/antlr-4.13.2-complete.jar prev26lang.Compiler program.p26
java -jar rars.jar rv64 nc program.asm
```

`rv64` selects the 64-bit simulator and `nc` suppresses the copyright banner.

A small wrapper script makes this a one-liner. Save it as `prev26c` somewhere on your `PATH`, with `PREV26` pointing to this repository:

```sh
#!/bin/sh
PREV26="$HOME/prev26"
java -cp "$PREV26/bin:$PREV26/src:$PREV26/lib/antlr-4.13.2-complete.jar" \
     prev26lang.Compiler --logged-phase=none "$1" &&
java -jar "$PREV26/rars.jar" rv64 nc "${1%.p26}.asm"
```

### Options

| Option | Meaning | Default |
|---|---|---|
| `--target-phase=PHASE` | Stop after the given phase (`lexan`, `synan`, `abstr`, `seman`, `memory`, `imrgen`, `imrlin`, `asmgen`, `livean`, `regall`) | `all` |
| `--logged-phase=PHASE` | Write the given phase's output as XML (`none`, `all`, or a phase name) | `all` |
| `--num-regs=K` | Number of physical registers available to the allocator (1–25) | `25` |
| `--dst-file-name=FILE` | Output file | `<source>.asm` |

For example, to see how much spill code a program needs with only six registers:

```sh
java -cp bin:src:lib/antlr-4.13.2-complete.jar prev26lang.Compiler --num-regs=6 prg/hanoi.p26
```

### Inspecting phases

The `prg/` Makefile compiles a program and turns the XML log of each phase into an HTML page (requires `xsltproc`):

```sh
cd prg
make hanoi TARGETPHASE=imrgen   # writes hanoi-imrgen.html with the IR trees
```

## Example programs

`prg/` contains test programs used during development, including:

- `hanoi.p26`: Towers of Hanoi (recursion)
- `8kraljic.p26`: eight queens by backtracking, which prints the solution that has a queen on a user-chosen square and draws the board
- `matrix.p26`: matrix operations using heap-allocated memory, with a C reference version in `matrix.c`
- `test0.p26` … `test23.p26`: smaller tests for individual language features

## Repository layout

```
src/prev26lang/
  Compiler.java        driver: command line, phase sequencing, runtime and final emission
  common/              error reporting and XML logging
  phase/lexan, synan   ANTLR grammars (.g4) and wrappers
  phase/abstr          AST
  phase/seman          name resolution, type construction, type checking
  phase/memory         frames and memory layout
  phase/imrgen         intermediate representation
  phase/imrlin         canonicalisation, basic blocks, trace scheduling, IR interpreter
  phase/asmgen         instruction selection
  phase/livean         flow graph, liveness, interference graph
  phase/regall         register allocation
lib/                   ANTLR jar, XSL stylesheets for phase logs
prg/                   example and test programs
```

## Acknowledgements

The PREV'26 language specification, the overall phase structure and parts of the framework (driver skeleton, logging, XSL stylesheets) come from the course. The algorithms follow Andrew W. Appel, *Modern Compiler Implementation in Java*, adapted to RISC-V and to the course's calling convention.
