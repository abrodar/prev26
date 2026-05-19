.data
_heap:
   .space 65536
_heap_ptr:
   .space 8
_memo:
   .space 968
_memoix:
   .space 968
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
_get_sizes:
   addi sp, sp, -32
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
L3:
L0:
   ld x30, 16(s0)
   li x9, 0
   bgt x30, x9, L1
L2:
   ld x9, 8(s0)
   ld a0, 0(x9)
   mv a0, a0
   j L4
L1:
   ld x9, 8(s0)
   addi x9, x9, 8
   sd x9, 8(s0)
   ld x30, 16(s0)
   li x9, 1
   sub x9, x30, x9
   sd x9, 16(s0)
   j L0
L4:
   ld x9, 0(sp)
   ld x30, 8(sp)
   ld s0, 16(sp)
   ld ra, 24(sp)
   addi sp, sp, 32
   ret
_st_mnozenj:
   addi sp, sp, -152
   sd s0, 80(sp)
   sd ra, 88(sp)
   addi s0, sp, 152
   sd x9, 32(sp)
   sd x11, 40(sp)
   sd x12, 48(sp)
   sd x14, 56(sp)
   sd x30, 64(sp)
   sd x31, 72(sp)
L13:
   ld x9, 16(s0)
   ld x30, 24(s0)
   beq x9, x30, L10
L11:
   li x9, 9223372036854775807
   sd x9, -16(s0)
   ld x9, 16(s0)
   sd x9, -24(s0)
   ld x9, 16(s0)
   sd x9, -32(s0)
L7:
   ld x30, -32(s0)
   ld x9, 24(s0)
   blt x30, x9, L8
L9:
   ld x12, -16(s0)
   la x9, _memo
   ld x30, 16(s0)
   li x31, 88
   mul x30, x30, x31
   add x30, x9, x30
   ld x31, 24(s0)
   li x9, 8
   mul x9, x31, x9
   add x9, x30, x9
   sd x12, 0(x9)
   ld x31, -24(s0)
   la x30, _memoix
   ld x12, 16(s0)
   li x9, 88
   mul x9, x12, x9
   add x30, x30, x9
   ld x12, 24(s0)
   li x9, 8
   mul x9, x12, x9
   add x9, x30, x9
   sd x31, 0(x9)
   ld x9, -16(s0)
   sd x9, -8(s0)
L12:
   ld a0, -8(s0)
   mv a0, a0
   j L14
L5:
   ld x9, -56(s0)
   sd x9, -16(s0)
   ld x9, -32(s0)
   sd x9, -24(s0)
L6:
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L7
L10:
   li x9, 0
   sd x9, -8(s0)
   j L12
L8:
   la x12, _st_mnozenj
   li x30, 0
   ld x11, 8(s0)
   ld x31, 16(s0)
   ld x9, -32(s0)
   sd x30, 0(sp)
   sd x11, 8(sp)
   sd x31, 16(sp)
   sd x9, 24(sp)
   jalr ra, x12, 0
   mv x9, a0
   sd x9, -40(s0)
   la x11, _st_mnozenj
   li x30, 0
   ld x31, 8(s0)
   ld x9, -32(s0)
   addi x9, x9, 1
   ld x12, 24(s0)
   sd x30, 0(sp)
   sd x31, 8(sp)
   sd x9, 16(sp)
   sd x12, 24(sp)
   jalr ra, x11, 0
   mv x9, a0
   sd x9, -48(s0)
   ld x9, -40(s0)
   ld x30, -48(s0)
   add x31, x9, x30
   mv x31, x31
   la x9, _get_sizes
   li x11, 0
   ld x30, 8(s0)
   ld x12, 16(s0)
   li x14, 1
   sub x12, x12, x14
   sd x11, 0(sp)
   sd x30, 8(sp)
   sd x12, 16(sp)
   jalr ra, x9, 0
   mv x9, a0
   mv x9, x9
   la x12, _get_sizes
   li x11, 0
   ld x14, 8(s0)
   ld x30, -32(s0)
   sd x11, 0(sp)
   sd x14, 8(sp)
   sd x30, 16(sp)
   jalr ra, x12, 0
   mv x30, a0
   mul x9, x9, x30
   mv x9, x9
   la x14, _get_sizes
   li x30, 0
   ld x12, 8(s0)
   ld x11, 24(s0)
   sd x30, 0(sp)
   sd x12, 8(sp)
   sd x11, 16(sp)
   jalr ra, x14, 0
   mv x30, a0
   mul x9, x9, x30
   add x9, x31, x9
   sd x9, -56(s0)
   ld x30, -56(s0)
   ld x9, -16(s0)
   blt x30, x9, L5
L23:
   j L6
L14:
   ld x9, 32(sp)
   ld x11, 40(sp)
   ld x12, 48(sp)
   ld x14, 56(sp)
   ld x30, 64(sp)
   ld x31, 72(sp)
   ld s0, 80(sp)
   ld ra, 88(sp)
   addi sp, sp, 152
   ret
_main:
   addi sp, sp, -136
   sd s0, 72(sp)
   sd ra, 80(sp)
   addi s0, sp, 136
   sd x9, 32(sp)
   sd x11, 40(sp)
   sd x12, 48(sp)
   sd x30, 56(sp)
   sd x31, 64(sp)
L21:
   la x30, _getint
   li x9, 0
   sd x9, 0(sp)
   jalr ra, x30, 0
   mv x9, a0
   sd x9, -8(s0)
   li x9, 0
   sd x9, -32(s0)
   la x12, _new
   li x31, 0
   li x30, 8
   ld x9, -8(s0)
   mul x9, x30, x9
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x12, 0
   mv x9, a0
   sd x9, -16(s0)
   ld x9, -16(s0)
   sd x9, -24(s0)
   la x30, _putint
   li x31, 0
   ld x9, -16(s0)
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   la x12, _putchar
   li x31, 0
   li x30, 10
   li x9, 256
   rem x9, x30, x9
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x12, 0
L18:
   ld x9, -32(s0)
   ld x30, -8(s0)
   blt x9, x30, L19
L20:
   la x31, _st_mnozenj
   li x12, 0
   ld x9, -24(s0)
   li x11, 1
   ld x30, -8(s0)
   sd x12, 0(sp)
   sd x9, 8(sp)
   sd x11, 16(sp)
   sd x30, 24(sp)
   jalr ra, x31, 0
   mv a0, a0
   la x30, _putint
   li x9, 0
   sd x9, 0(sp)
   sd a0, 8(sp)
   jalr ra, x30, 0
   la x9, _putchar
   li x31, 0
   li x30, 10
   li x12, 256
   rem x30, x30, x12
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   li a0, 1
   mv a0, a0
   j L22
L15:
   ld x9, -40(s0)
   ld x30, -16(s0)
   sd x9, 0(x30)
   ld x9, -16(s0)
   addi x9, x9, 8
   sd x9, -16(s0)
   ld x30, -48(s0)
   ld x9, -16(s0)
   sd x30, 0(x9)
   j L17
L19:
   la x30, _putint
   li x31, 0
   ld x9, -32(s0)
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   la x12, _putchar
   li x31, 0
   li x30, 10
   li x9, 256
   rem x9, x30, x9
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x12, 0
   la x9, _getint
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -40(s0)
   la x9, _getint
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -48(s0)
   ld x9, -32(s0)
   li x30, 0
   beq x9, x30, L15
L16:
   ld x30, -48(s0)
   ld x9, -16(s0)
   sd x30, 0(x9)
L17:
   ld x9, -16(s0)
   addi x9, x9, 8
   sd x9, -16(s0)
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L18
L22:
   ld x9, 32(sp)
   ld x11, 40(sp)
   ld x12, 48(sp)
   ld x30, 56(sp)
   ld x31, 64(sp)
   ld s0, 72(sp)
   ld ra, 80(sp)
   addi sp, sp, 136
   ret
