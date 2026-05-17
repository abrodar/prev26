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
_fib:
   addi sp, sp, -80
   sd s0, 56(sp)
   sd ra, 64(sp)
   addi s0, sp, 80
   sd x9, 16(sp)
   sd x11, 24(sp)
   sd x12, 32(sp)
   sd x30, 40(sp)
   sd x31, 48(sp)
L3:
   ld x9, 8(s0)
   li x30, 1
   ble x9, x30, L0
L1:
   la x30, _fib
   li x31, 0
   ld x9, 8(s0)
   li x12, 1
   sub x9, x9, x12
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   mv x9, a0
   mv x9, x9
   la x12, _fib
   li x30, 0
   ld x31, 8(s0)
   li x11, 2
   sub x31, x31, x11
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x12, 0
   mv a0, a0
   add x9, x9, a0
   sd x9, -8(s0)
L2:
   ld a0, -8(s0)
   mv a0, a0
   j L4
L0:
   ld x9, 8(s0)
   sd x9, -8(s0)
   j L2
L4:
   ld x9, 16(sp)
   ld x11, 24(sp)
   ld x12, 32(sp)
   ld x30, 40(sp)
   ld x31, 48(sp)
   ld s0, 56(sp)
   ld ra, 64(sp)
   addi sp, sp, 80
   ret
_main:
   addi sp, sp, -56
   sd s0, 40(sp)
   sd ra, 48(sp)
   addi s0, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L5:
   la x9, _fib
   li x31, 0
   li x30, 10
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   mv a0, a0
L6:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
