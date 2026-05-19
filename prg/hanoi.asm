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
_solve:
   addi sp, sp, -104
   sd s0, 88(sp)
   sd ra, 96(sp)
   addi s0, sp, 104
   sd x9, 40(sp)
   sd x11, 48(sp)
   sd x12, 56(sp)
   sd x14, 64(sp)
   sd x30, 72(sp)
   sd x31, 80(sp)
L3:
   ld x9, 8(s0)
   li x30, 1
   beq x9, x30, L0
L1:
   la x31, _solve
   li x11, 0
   ld x30, 8(s0)
   li x9, 1
   sub x30, x30, x9
   lb x9, 16(s0)
   lb x14, 32(s0)
   lb x12, 24(s0)
   sd x11, 0(sp)
   sd x30, 8(sp)
   sd x9, 16(sp)
   sd x14, 24(sp)
   sd x12, 32(sp)
   jalr ra, x31, 0
   la x9, _solve
   li x30, 0
   li x31, 1
   lb x14, 16(s0)
   lb x12, 24(s0)
   lb x11, 32(s0)
   sd x30, 0(sp)
   sd x31, 8(sp)
   sd x14, 16(sp)
   sd x12, 24(sp)
   sd x11, 32(sp)
   jalr ra, x9, 0
   la x31, _solve
   li x30, 0
   ld x12, 8(s0)
   li x9, 1
   sub x11, x12, x9
   lb x14, 32(s0)
   lb x9, 24(s0)
   lb x12, 16(s0)
   sd x30, 0(sp)
   sd x11, 8(sp)
   sd x14, 16(sp)
   sd x9, 24(sp)
   sd x12, 32(sp)
   jalr ra, x31, 0
L2:
   li a0, 0
   mv a0, a0
   j L4
L0:
   la x31, _putchar
   li x9, 0
   lb x30, 16(s0)
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   la x31, _putchar
   li x9, 0
   li x30, 62
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   la x30, _putchar
   li x9, 0
   lb x31, 24(s0)
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   la x12, _putchar
   li x30, 0
   li x31, 10
   li x9, 256
   rem x9, x31, x9
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x12, 0
   j L2
L4:
   ld x9, 40(sp)
   ld x11, 48(sp)
   ld x12, 56(sp)
   ld x14, 64(sp)
   ld x30, 72(sp)
   ld x31, 80(sp)
   ld s0, 88(sp)
   ld ra, 96(sp)
   addi sp, sp, 104
   ret
_main:
   addi sp, sp, -112
   sd s0, 88(sp)
   sd ra, 96(sp)
   addi s0, sp, 112
   sd x9, 40(sp)
   sd x11, 48(sp)
   sd x12, 56(sp)
   sd x14, 64(sp)
   sd x30, 72(sp)
   sd x31, 80(sp)
L5:
   la x30, _getint
   li x9, 0
   sd x9, 0(sp)
   jalr ra, x30, 0
   mv a0, a0
   sd a0, -8(s0)
   la x12, _solve
   li x11, 0
   ld x30, -8(s0)
   li x14, 65
   li x31, 67
   li x9, 66
   sd x11, 0(sp)
   sd x30, 8(sp)
   sd x14, 16(sp)
   sd x31, 24(sp)
   sd x9, 32(sp)
   jalr ra, x12, 0
   li a0, 0
   mv a0, a0
L6:
   ld x9, 40(sp)
   ld x11, 48(sp)
   ld x12, 56(sp)
   ld x14, 64(sp)
   ld x30, 72(sp)
   ld x31, 80(sp)
   ld s0, 88(sp)
   ld ra, 96(sp)
   addi sp, sp, 112
   ret
