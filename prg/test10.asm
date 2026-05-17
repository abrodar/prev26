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
   addi sp, sp, -56
   sd s0, 32(sp)
   sd ra, 40(sp)
   addi s0, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
L3:
   li x9, 10
   sd x9, -8(s0)
   la x9, L0
   li x30, 3
   sd s0, 0(sp)
   sd x30, 8(sp)
   jalr ra, x9, 0
   mv a0, a0
L4:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld s0, 32(sp)
   ld ra, 40(sp)
   addi sp, sp, 56
   ret
L0:
   addi sp, sp, -32
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
L1:
   ld x9, 0(s0)
   ld x30, -8(x9)
   ld x9, 8(s0)
   add a0, x30, x9
   mv a0, a0
L2:
   ld x9, 0(sp)
   ld x30, 8(sp)
   ld s0, 16(sp)
   ld ra, 24(sp)
   addi sp, sp, 32
   ret
