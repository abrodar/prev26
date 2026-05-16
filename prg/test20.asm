.data
.text
_start:
    li sp, 0x7FFFFF00
    jal ra, _main
    li a7, 10
    ecall
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
   la x31, _fib
   li x9, 0
   ld x30, 8(s0)
   li x12, 1
   sub x30, x30, x12
   sd x9, 0(sp)
   sd x30, 8(sp)
   jalr ra, x31, 0
   mv x9, a0
   mv x9, x9
   la x11, _fib
   li x31, 0
   ld x30, 8(s0)
   li x12, 2
   sub x30, x30, x12
   sd x31, 0(sp)
   sd x30, 8(sp)
   jalr ra, x11, 0
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
