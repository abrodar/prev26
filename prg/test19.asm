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
   addi sp, sp, -64
   sd s0, 40(sp)
   sd ra, 48(sp)
   addi s0, sp, 64
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L0:
   li x9, 5
   sd x9, -8(s0)
   la x9, _putint
   li x30, 0
   ld x31, -8(s0)
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   la x30, _exit
   li x9, 0
   sd x9, 0(sp)
   jalr ra, x30, 0
   la x31, _putint
   li x9, 0
   li x30, 999
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   li a0, 0
   mv a0, a0
L1:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 64
   ret
