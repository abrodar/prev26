.data
.text
_start:
    li sp, 0x7FFFFF00
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
_main:
   addi sp, sp, -120
   sd s0, 56(sp)
   sd ra, 64(sp)
   addi s0, sp, 120
   sd x9, 16(sp)
   sd x11, 24(sp)
   sd x12, 32(sp)
   sd x30, 40(sp)
   sd x31, 48(sp)
L11:
   li x30, 3
   addi x9, s0, -24
   li x12, 0
   li x31, 8
   mul x31, x12, x31
   add x9, x9, x31
   sd x30, 0(x9)
   li x30, 1
   addi x12, s0, -24
   li x9, 1
   li x31, 8
   mul x9, x9, x31
   add x9, x12, x9
   sd x30, 0(x9)
   li x31, 2
   addi x30, s0, -24
   li x12, 2
   li x9, 8
   mul x9, x12, x9
   add x9, x30, x9
   sd x31, 0(x9)
   li x9, 0
   sd x9, -32(s0)
L5:
   ld x9, -32(s0)
   li x30, 3
   blt x9, x30, L6
L7:
   li x9, 0
   sd x9, -32(s0)
L8:
   ld x9, -32(s0)
   li x30, 3
   blt x9, x30, L9
L10:
   li a0, 0
   mv a0, a0
   j L12
L4:
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L5
L9:
   la x30, _putint
   li x9, 0
   addi x11, s0, -24
   ld x12, -32(s0)
   li x31, 8
   mul x31, x12, x31
   add x31, x11, x31
   ld x31, 0(x31)
   sd x9, 0(sp)
   sd x31, 8(sp)
   jalr ra, x30, 0
   la x31, _putchar
   li x9, 0
   li x30, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   ld x9, -32(s0)
   addi x9, x9, 1
   sd x9, -32(s0)
   j L8
L3:
   addi x9, s0, -24
   ld x30, -40(s0)
   li x31, 8
   mul x30, x30, x31
   add x9, x9, x30
   ld x30, 0(x9)
   addi x12, s0, -24
   ld x9, -40(s0)
   addi x31, x9, 1
   li x9, 8
   mul x9, x31, x9
   add x9, x12, x9
   ld x9, 0(x9)
   slt x9, x9, x30
   beqz x9, L1
L0:
   addi x9, s0, -24
   ld x30, -40(s0)
   li x31, 8
   mul x30, x30, x31
   add x9, x9, x30
   ld x9, 0(x9)
   sd x9, -48(s0)
   addi x30, s0, -24
   ld x9, -40(s0)
   addi x31, x9, 1
   li x9, 8
   mul x9, x31, x9
   add x9, x30, x9
   ld x30, 0(x9)
   addi x31, s0, -24
   ld x12, -40(s0)
   li x9, 8
   mul x9, x12, x9
   add x9, x31, x9
   sd x30, 0(x9)
   ld x30, -48(s0)
   addi x12, s0, -24
   ld x9, -40(s0)
   addi x9, x9, 1
   li x31, 8
   mul x9, x9, x31
   add x9, x12, x9
   sd x30, 0(x9)
L1:
   ld x9, -40(s0)
   addi x9, x9, 1
   sd x9, -40(s0)
   j L2
L6:
   li x9, 0
   sd x9, -40(s0)
L2:
   ld x31, -40(s0)
   li x9, 2
   ld x30, -32(s0)
   sub x9, x9, x30
   blt x31, x9, L3
L13:
   j L4
L12:
   ld x9, 16(sp)
   ld x11, 24(sp)
   ld x12, 32(sp)
   ld x30, 40(sp)
   ld x31, 48(sp)
   ld s0, 56(sp)
   ld ra, 64(sp)
   addi sp, sp, 120
   ret
