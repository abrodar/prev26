.data
_heap:
   .space 65536
_heap_ptr:
   .space 8
_a:
   .space 8
_b:
   .space 8
_row:
   .space 64
.text
_start:
    li sp, 0x7FFFFF00
    la t0, _heap
    la t1, _heap_ptr
    sd t0, 0(t1)
    jal ra, _main
    li a7, 10
    ecall
_putint:
   addi sp, sp, -24
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a7, 16(sp)
   addi s0, sp, 24
   ld a0, 8(s0)
   li a7, 1
   ecall
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a7, 16(sp)
   addi sp, sp, 24
   ret
_putchar:
   addi sp, sp, -24
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a7, 16(sp)
   addi s0, sp, 24
   ld a0, 8(s0)
   li a7, 11
   ecall
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a7, 16(sp)
   addi sp, sp, 24
   ret
_getint:
   addi sp, sp, -24
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a7, 16(sp)
   addi s0, sp, 24
   li a7, 5
   ecall
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a7, 16(sp)
   addi sp, sp, 24
   ret
_getchar:
   addi sp, sp, -24
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a7, 16(sp)
   addi s0, sp, 24
   li a7, 12
   ecall
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a7, 16(sp)
   addi sp, sp, 24
   ret
_exit:
    li a7, 10
    ecall
_new:
   addi sp, sp, -32
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a7, 16(sp)
   sd a6, 24(sp)
   addi s0, sp, 32
   la a6, _heap_ptr
   ld a0, 0(a6)
   ld a7, 8(s0)
   add a7, a0, a7
   sd a7, 0(a6)
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a7, 16(sp)
   ld a6, 24(sp)
   addi sp, sp, 32
   ret
_del:
   ret
_abs:
   addi sp, sp, -32
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
L2:
   ld x9, 8(s0)
   li x30, 0
   slt x9, x9, x30
   beqz x9, L1
L0:
   li x9, 1
   neg x9, x9
   ld x30, 8(s0)
   mul x9, x9, x30
   sd x9, 8(s0)
L1:
   ld a0, 8(s0)
   mv a0, a0
L3:
   ld x9, 0(sp)
   ld x30, 8(sp)
   ld s0, 16(sp)
   ld ra, 24(sp)
   addi sp, sp, 32
   ret
_canPlace:
   addi sp, sp, -104
   sd s0, 72(sp)
   sd ra, 80(sp)
   addi s0, sp, 104
   sd x9, 16(sp)
   sd x11, 24(sp)
   sd x12, 32(sp)
   sd x13, 40(sp)
   sd x14, 48(sp)
   sd x30, 56(sp)
   sd x31, 64(sp)
L9:
   li x9, 0
   sd x9, -8(s0)
   li x9, 1
   sb x9, -16(s0)
L6:
   ld x30, -8(s0)
   ld x9, 16(s0)
   blt x30, x9, L7
L8:
   lb a0, -16(s0)
   mv a0, a0
   j L10
L4:
   li x9, 0
   sb x9, -16(s0)
L5:
   ld x9, -8(s0)
   addi x9, x9, 1
   sd x9, -8(s0)
   j L6
L7:
   la x30, _row
   ld x9, -8(s0)
   li x31, 8
   mul x9, x9, x31
   add x9, x30, x9
   ld x30, 0(x9)
   ld x9, 8(s0)
   xor x9, x30, x9
   seqz x9, x9
   mv x9, x9
   la x12, _abs
   li x30, 0
   ld x31, -8(s0)
   ld x11, 16(s0)
   sub x31, x31, x11
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x12, 0
   mv x30, a0
   mv x30, x30
   la x14, _abs
   li x12, 0
   la x11, _row
   ld x31, -8(s0)
   li x13, 8
   mul x31, x31, x13
   add x31, x11, x31
   ld x11, 0(x31)
   ld x31, 8(s0)
   sub x31, x11, x31
   sd x12, 0(sp)
   sd x31, 8(sp)
   jalr ra, x14, 0
   mv x31, a0
   xor x30, x30, x31
   seqz x30, x30
   or x9, x9, x30
   bnez x9, L4
L41:
   j L5
L10:
   ld x9, 16(sp)
   ld x11, 24(sp)
   ld x12, 32(sp)
   ld x13, 40(sp)
   ld x14, 48(sp)
   ld x30, 56(sp)
   ld x31, 64(sp)
   ld s0, 72(sp)
   ld ra, 80(sp)
   addi sp, sp, 104
   ret
_backtrack:
   addi sp, sp, -112
   sd s0, 64(sp)
   sd ra, 72(sp)
   addi s0, sp, 112
   sd x9, 24(sp)
   sd x11, 32(sp)
   sd x12, 40(sp)
   sd x30, 48(sp)
   sd x31, 56(sp)
L37:
   ld x9, 8(s0)
   li x30, 8
   xor x31, x9, x30
   seqz x31, x31
   la x12, _row
   la x9, _b
   ld x9, 0(x9)
   li x30, 8
   mul x9, x9, x30
   add x9, x12, x9
   ld x9, 0(x9)
   la x30, _a
   ld x30, 0(x30)
   xor x9, x9, x30
   seqz x9, x9
   and x9, x31, x9
   bnez x9, L34
L35:
   li x9, 0
   sd x9, -32(s0)
L31:
   ld x9, -32(s0)
   li x30, 8
   blt x9, x30, L32
L33:
L36:
   li a0, 0
   mv a0, a0
   j L38
L13:
   la x9, _putchar
   li x31, 0
   li x12, 10
   li x30, 256
   rem x30, x12, x30
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   li x9, 7
   sd x9, -16(s0)
   li x9, 0
   sd x9, -24(s0)
L23:
   ld x30, -16(s0)
   li x9, 0
   bge x30, x9, L24
L25:
   j L36
L28:
   j L30
L34:
   la x31, _putint
   li x12, 0
   la x9, _row
   li x30, 0
   li x11, 8
   mul x30, x30, x11
   add x9, x9, x30
   ld x9, 0(x9)
   addi x9, x9, 1
   sd x12, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
   li x9, 1
   sd x9, -8(s0)
L11:
   ld x9, -8(s0)
   li x30, 8
   slt x9, x9, x30
   beqz x9, L13
L12:
   la x9, _putchar
   li x31, 0
   li x30, 32
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   la x12, _putint
   li x11, 0
   la x9, _row
   ld x30, -8(s0)
   li x31, 8
   mul x30, x30, x31
   add x9, x9, x30
   ld x9, 0(x9)
   addi x9, x9, 1
   sd x11, 0(sp)
   sd x9, 8(sp)
   jalr ra, x12, 0
   ld x9, -8(s0)
   addi x9, x9, 1
   sd x9, -8(s0)
   j L11
L26:
   ld x12, -32(s0)
   la x30, _row
   ld x9, 8(s0)
   li x31, 8
   mul x9, x9, x31
   add x9, x30, x9
   sd x12, 0(x9)
   la x31, _backtrack
   li x30, 0
   ld x9, 8(s0)
   addi x9, x9, 1
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
L27:
L30:
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L31
L14:
   la x9, _putchar
   li x31, 0
   li x30, 89
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   la x9, _putchar
   li x30, 0
   li x31, 32
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   j L16
L29:
   la x30, _canPlace
   li x31, 0
   ld x9, -32(s0)
   ld x12, 8(s0)
   sd x31, 0(sp)
   sd x9, 8(sp)
   sd x12, 16(sp)
   jalr ra, x30, 0
   mv x9, a0
   bnez x9, L26
L42:
   j L27
L32:
   ld x30, 8(s0)
   la x9, _b
   ld x9, 0(x9)
   xor x9, x30, x9
   seqz x9, x9
   ld x31, -32(s0)
   la x30, _a
   ld x30, 0(x30)
   xor x30, x31, x30
   snez x30, x30
   and x9, x9, x30
   bnez x9, L28
L43:
   j L29
L17:
   ld x30, -16(s0)
   la x9, _a
   ld x9, 0(x9)
   xor x30, x30, x9
   seqz x30, x30
   ld x31, -24(s0)
   la x9, _b
   ld x9, 0(x9)
   xor x9, x31, x9
   seqz x9, x9
   and x9, x30, x9
   bnez x9, L14
L15:
   la x31, _putchar
   li x9, 0
   li x30, 88
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   la x31, _putchar
   li x30, 0
   li x9, 32
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
L16:
   j L19
L22:
   la x9, _putchar
   li x31, 0
   li x12, 10
   li x30, 256
   rem x30, x12, x30
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   ld x30, -16(s0)
   li x9, 1
   sub x9, x30, x9
   sd x9, -16(s0)
   j L23
L18:
   la x30, _putchar
   li x9, 0
   li x31, 45
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   la x9, _putchar
   li x31, 0
   li x30, 32
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
L19:
   ld x9, -24(s0)
   addi x9, x9, 1
   sd x9, -24(s0)
   j L20
L24:
   li x9, 0
   sd x9, -24(s0)
L20:
   ld x30, -24(s0)
   li x9, 8
   slt x9, x30, x9
   beqz x9, L22
L21:
   la x30, _row
   ld x31, -24(s0)
   li x9, 8
   mul x9, x31, x9
   add x9, x30, x9
   ld x9, 0(x9)
   ld x30, -16(s0)
   beq x9, x30, L17
L44:
   j L18
L38:
   ld x9, 24(sp)
   ld x11, 32(sp)
   ld x12, 40(sp)
   ld x30, 48(sp)
   ld x31, 56(sp)
   ld s0, 64(sp)
   ld ra, 72(sp)
   addi sp, sp, 112
   ret
_main:
   addi sp, sp, -56
   sd s0, 40(sp)
   sd ra, 48(sp)
   addi s0, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L39:
   la x9, _getint
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv x9, a0
   la x30, _a
   sd x9, 0(x30)
   la x30, _getint
   li x9, 0
   sd x9, 0(sp)
   jalr ra, x30, 0
   mv a0, a0
   la x9, _b
   sd a0, 0(x9)
   la x30, _a
   ld x30, 0(x30)
   li x9, 1
   sub x30, x30, x9
   la x9, _a
   sd x30, 0(x9)
   la x30, _b
   ld x30, 0(x30)
   li x9, 1
   sub x30, x30, x9
   la x9, _b
   sd x30, 0(x9)
   la x9, _backtrack
   li x31, 0
   li x30, 0
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   li a0, 1
   mv a0, a0
L40:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
