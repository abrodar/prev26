.data
.text
_start:
    li sp, 0x7FFFFF00
    jal ra, _main
    li a7, 10
    ecall
_double:
   addi sp, sp, -32
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 32
   sd x9, 0(sp)
   sd x30, 8(sp)
L0:
   ld x30, 8(null)
   li x9, 2
   mul a0, x30, x9
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
   ld x9, 8(null)
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
   la x31, _double
   li x9, 0
   li x30, 5
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   mv x9, a0
   la x30, _inc
   li x31, 0
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   mv x9, a0
   la x30, _double
   li x31, 0
   sd x31, 0(sp)
   sd x9, 8(sp)
   jalr ra, x30, 0
   mv a0, a0
L5:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld s0, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
