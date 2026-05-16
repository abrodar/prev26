.data
.text
#### _fib ####
   addi sp, sp, -80
   sd fp, 56(sp)
   sd ra, 64(sp)
   addi fp, sp, 80
   sd x9, 16(sp)
   sd x11, 24(sp)
   sd x12, 32(sp)
   sd x30, 40(sp)
   sd x31, 48(sp)
L3:
   ld x30, 8(fp)
   li x9, 1
   ble x30, x9, L0
L1:
   la x31, _fib
   li x30, 0
   ld x9, 8(fp)
   li x12, 1
   sub x9, x9, x12
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, 0(x31)
   mv x9, a0
   mv x9, x9
   la x30, _fib
   li x11, 0
   ld x31, 8(fp)
   li x12, 2
   sub x31, x31, x12
   sd x11, 0(sp)
   sd x31, 8(sp)
   jalr ra, 0(x30)
   mv a0, a0
   add x9, x9, a0
   sd x9, -8(fp)
L2:
   ld a0, -8(fp)
   mv a0, a0
L4:
   ld x9, 16(sp)
   ld x11, 24(sp)
   ld x12, 32(sp)
   ld x30, 40(sp)
   ld x31, 48(sp)
   ld fp, 56(sp)
   ld ra, 64(sp)
   addi sp, sp, 80
   ret
   j L7
L0:
   ld x9, 8(fp)
   sd x9, -8(fp)
   j L2
L7:
#### _main ####
   addi sp, sp, -56
   sd fp, 40(sp)
   sd ra, 48(sp)
   addi fp, sp, 56
   sd x9, 16(sp)
   sd x30, 24(sp)
   sd x31, 32(sp)
L5:
   la x31, _fib
   li x30, 0
   li x9, 10
   sd x30, 0(sp)
   sd x9, 8(sp)
   jalr ra, 0(x31)
   mv a0, a0
L6:
   ld x9, 16(sp)
   ld x30, 24(sp)
   ld x31, 32(sp)
   ld fp, 40(sp)
   ld ra, 48(sp)
   addi sp, sp, 56
   ret
L8:
