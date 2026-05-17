.data
_input_buff: 
   .byte 0, 0, 0, 0, 0, 0, 0, 0
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
   ret
_getchar:
   addi sp, sp, -40
   sd s0, 0(sp)
   sd ra, 8(sp)
   sd a1, 16(sp)
   sd a2, 24(sp)
   sd a7, 32(sp)
   addi s0, sp, 40
   li a7, 63
   li a0, 0
   la a1, _input_buff
   li a2, 1
   ecall
   la a0, _input_buff
   lb a0, 0(a0)
   ld s0, 0(sp)
   ld ra, 8(sp)
   ld a1, 16(sp)
   ld a2, 24(sp)
   ld a7, 32(sp)
   addi sp, sp, 40
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
   la x9, _getchar
   li x30, 0
   sd x30, 0(sp)
   jalr ra, x9, 0
   mv a0, a0
   sb a0, -8(s0)
   la x9, _putchar
   li x30, 0
   lb x31, -8(s0)
   sd x30, 0(sp)
   sd x31, 8(sp)
   jalr ra, x9, 0
   li a0, 1
   mv a0, a0
L1:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 64
   ret
