.data
_heap:
   .space 65536
_heap_ptr:
   .space 8
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
_a:
   addi sp, sp, -72
   sd s0, 48(sp)
   sd ra, 56(sp)
   addi s0, sp, 72
   sd x9, 16(sp)
   sd x12, 24(sp)
   sd x30, 32(sp)
   sd x31, 40(sp)
L0:
   la x30, _new
   li x9, 0
   li x31, 8
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   mv a0, a0
   sd a0, -8(s0)
   li x30, 3
   ld x9, -8(s0)
   sd x30, 0(x9)
   la x9, _putint
   li x31, 0
   ld x30, -8(s0)
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   la x9, _putchar
   li x30, 0
   li x12, 10
   li x31, 256
   rem x31, x12, x31
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   ld a0, -8(s0)
   mv a0, a0
L1:
   ld x9, 16(sp)
   ld x12, 24(sp)
   ld x30, 32(sp)
   ld x31, 40(sp)
   ld s0, 48(sp)
   ld ra, 56(sp)
   addi sp, sp, 72
   ret
_main:
   addi sp, sp, -56
   sd s0, 40(sp)
   sd ra, 48(sp)
   addi s0, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L2:
   la x9, _a
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv a0, a0
   la x9, _putint
   li x30, 0
   addi x31, a0, 7
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   li a0, 300
   mv a0, a0
L3:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
