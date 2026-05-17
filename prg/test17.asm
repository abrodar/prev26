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
_double:
   addi sp, sp, -32
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
L0:
   ld x9, 8(s0)
   li x30, 2
   mul a0, x9, x30
   mv a0, a0
L1:
   ld x9, 0(sp)
   ld x30, 8(sp)
   ld s0, 16(sp)
   ld ra, 24(sp)
   addi sp, sp, 32
   ret
_inc:
   addi sp, sp, -24
   sd s0, 8(sp)
   sd ra, 16(sp)
   addi s0, sp, 24
   sd x9, 0(sp)
L2:
   ld x9, 8(s0)
   addi a0, x9, 1
   mv a0, a0
L3:
   ld x9, 0(sp)
   ld s0, 8(sp)
   ld ra, 16(sp)
   addi sp, sp, 24
   ret
_main:
   addi sp, sp, -56
   sd s0, 40(sp)
   sd ra, 48(sp)
   addi s0, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L4:
   la x9, _double
   li x30, 0
   li x31, 5
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   mv x9, a0
   la x30, _inc
   li x31, 0
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   mv x9, a0
   la x31, _double
   li x30, 0
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
   mv a0, a0
L5:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
