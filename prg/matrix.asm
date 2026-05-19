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
   ld x9, 16(s0)
   li x30, 1
   sub x9, x9, x30
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
   ld x30, 16(s0)
   ld x9, 24(s0)
   beq x30, x9, L10
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
   ld x31, -16(s0)
   la x12, _memo
   ld x30, 16(s0)
   li x9, 88
   mul x9, x30, x9
   add x12, x12, x9
   ld x30, 24(s0)
   li x9, 8
   mul x9, x30, x9
   add x9, x12, x9
   sd x31, 0(x9)
   ld x9, -24(s0)
   la x12, _memoix
   ld x31, 16(s0)
   li x30, 88
   mul x30, x31, x30
   add x12, x12, x30
   ld x31, 24(s0)
   li x30, 8
   mul x30, x31, x30
   add x30, x12, x30
   sd x9, 0(x30)
   ld x9, -16(s0)
   sd x9, -8(s0)
L12:
   ld a0, -8(s0)
   mv a0, a0
   j L14
L8:
   la x9, _st_mnozenj
   li x11, 0
   ld x31, 8(s0)
   ld x30, 16(s0)
   ld x12, -32(s0)
   sd x11, 0(sp)
   sd x31, 8(sp)
   sd x30, 16(sp)
   sd x12, 24(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -40(s0)
   la x9, _st_mnozenj
   li x11, 0
   ld x30, 8(s0)
   ld x31, -32(s0)
   addi x31, x31, 1
   ld x12, 24(s0)
   sd x11, 0(sp)
   sd x30, 8(sp)
   sd x31, 16(sp)
   sd x12, 24(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -48(s0)
   ld x9, -40(s0)
   ld x30, -48(s0)
   add x31, x9, x30
   mv x31, x31
   la x9, _get_sizes
   li x14, 0
   ld x30, 8(s0)
   ld x11, 16(s0)
   li x12, 1
   sub x12, x11, x12
   sd x14, 0(sp)
   sd x30, 8(sp)
   sd x12, 16(sp)
   jalr ra, x9, 0
   mv x9, a0
   mv x9, x9
   la x12, _get_sizes
   li x14, 0
   ld x30, 8(s0)
   ld x11, -32(s0)
   sd x14, 0(sp)
   sd x30, 8(sp)
   sd x11, 16(sp)
   jalr ra, x12, 0
   mv x30, a0
   mul x30, x9, x30
   mv x30, x30
   la x9, _get_sizes
   li x12, 0
   ld x11, 8(s0)
   ld x14, 24(s0)
   sd x12, 0(sp)
   sd x11, 8(sp)
   sd x14, 16(sp)
   jalr ra, x9, 0
   mv x9, a0
   mul x9, x30, x9
   add x9, x31, x9
   sd x9, -56(s0)
   ld x30, -56(s0)
   ld x9, -16(s0)
   slt x9, x30, x9
   beqz x9, L6
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
_oklepaji:
   addi sp, sp, -80
   sd s0, 56(sp)
   sd ra, 64(sp)
   addi s0, sp, 80
   sd x9, 24(sp)
   sd x12, 32(sp)
   sd x30, 40(sp)
   sd x31, 48(sp)
L26:
   ld x30, 8(s0)
   ld x9, 16(s0)
   beq x30, x9, L23
L24:
   la x9, _memoix
   ld x30, 8(s0)
   li x31, 88
   mul x30, x30, x31
   add x30, x9, x30
   ld x9, 16(s0)
   li x31, 8
   mul x9, x9, x31
   add x9, x30, x9
   ld x9, 0(x9)
   sd x9, -8(s0)
   ld x9, 8(s0)
   ld x30, -8(s0)
   xor x9, x9, x30
   snez x9, x9
   beqz x9, L16
L15:
   la x31, _putchar
   li x30, 0
   li x9, 40
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
L16:
   la x30, _oklepaji
   li x9, 0
   ld x12, 8(s0)
   ld x31, -8(s0)
   sd x9, 0(sp)
   sd x12, 8(sp)
   sd x31, 16(sp)
   jalr ra, x30, 0
   ld x30, 8(s0)
   ld x9, -8(s0)
   xor x9, x30, x9
   snez x9, x9
   beqz x9, L18
L17:
   la x30, _putchar
   li x31, 0
   li x9, 41
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
L18:
   la x31, _putchar
   li x30, 0
   li x9, 120
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
   ld x30, 16(s0)
   ld x9, -8(s0)
   addi x9, x9, 1
   xor x9, x30, x9
   snez x9, x9
   beqz x9, L20
L19:
   la x31, _putchar
   li x30, 0
   li x9, 40
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
L20:
   la x31, _oklepaji
   li x30, 0
   ld x9, -8(s0)
   addi x9, x9, 1
   ld x12, 16(s0)
   sd x30, 0(sp)
   sd x9, 8(sp)
   sd x12, 16(sp)
   jalr ra, x31, 0
   ld x30, 16(s0)
   ld x9, -8(s0)
   addi x9, x9, 1
   xor x9, x30, x9
   snez x9, x9
   beqz x9, L22
L21:
   la x9, _putchar
   li x30, 0
   li x31, 41
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
L22:
L25:
   li a0, 0
   mv a0, a0
   j L27
L23:
   la x31, _putchar
   li x30, 0
   li x9, 65
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
   la x30, _putint
   li x9, 0
   ld x31, 8(s0)
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   j L25
L27:
   ld x9, 24(sp)
   ld x12, 32(sp)
   ld x30, 40(sp)
   ld x31, 48(sp)
   ld s0, 56(sp)
   ld ra, 64(sp)
   addi sp, sp, 80
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
L34:
   la x9, _getint
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -8(s0)
   li x9, 0
   sd x9, -32(s0)
   la x30, _new
   li x31, 0
   li x9, 8
   ld x12, -8(s0)
   mul x9, x9, x12
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   mv x9, a0
   sd x9, -16(s0)
   ld x9, -16(s0)
   sd x9, -24(s0)
L31:
   ld x9, -32(s0)
   ld x30, -8(s0)
   blt x9, x30, L32
L33:
   la x30, _st_mnozenj
   li x9, 0
   ld x31, -24(s0)
   li x12, 1
   ld x11, -8(s0)
   sd x9, 0(sp)
   sd x31, 8(sp)
   sd x12, 16(sp)
   sd x11, 24(sp)
   jalr ra, x30, 0
   mv a0, a0
   la x30, _putint
   li x9, 0
   sd x9, 0(sp)
   sd a0, 8(sp)
   jalr ra, x30, 0
   la x31, _putchar
   li x30, 0
   li x12, 10
   li x9, 256
   rem x9, x12, x9
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, x31, 0
   la x30, _putchar
   li x31, 0
   li x9, 40
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   la x12, _oklepaji
   li x31, 0
   li x9, 1
   ld x30, -8(s0)
   sd x31, 0(sp)
   sd x9, 8(sp)
   sd x30, 16(sp)
   jalr ra, x12, 0
   la x30, _putchar
   li x9, 0
   li x31, 41
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   li a0, 1
   mv a0, a0
   j L35
L28:
   ld x30, -40(s0)
   ld x9, -16(s0)
   sd x30, 0(x9)
   ld x9, -16(s0)
   addi x9, x9, 8
   sd x9, -16(s0)
   ld x30, -48(s0)
   ld x9, -16(s0)
   sd x30, 0(x9)
   j L30
L32:
   la x30, _getint
   li x9, 0
   sd x9, 0(sp)
   jalr ra, x30, 0
   mv x9, a0
   sd x9, -40(s0)
   la x9, _getint
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv x9, a0
   sd x9, -48(s0)
   ld x30, -32(s0)
   li x9, 0
   beq x30, x9, L28
L29:
   ld x30, -48(s0)
   ld x9, -16(s0)
   sd x30, 0(x9)
L30:
   ld x9, -16(s0)
   addi x9, x9, 8
   sd x9, -16(s0)
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L31
L35:
   ld x9, 32(sp)
   ld x11, 40(sp)
   ld x12, 48(sp)
   ld x30, 56(sp)
   ld x31, 64(sp)
   ld s0, 72(sp)
   ld ra, 80(sp)
   addi sp, sp, 136
   ret
