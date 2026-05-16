.data
.text
L3:
ld x9, 8(fp)
li x30, 1
ble x9, x30, L0
L1:
la x12, _fib
li x31, 0
ld x9, 8(fp)
li x30, 1
sub x9, x9, x30
sd x31, 0(sp)
sd x9, 8(sp)
jalr ra, 0(x12)
mv x9, a0
mv x9, x9
la x11, _fib
li x31, 0
ld x12, 8(fp)
li x30, 2
sub x30, x12, x30
sd x31, 0(sp)
sd x30, 8(sp)
jalr ra, 0(x11)
mv a0, a0
add x9, x9, a0
sd x9, -8(fp)
L2:
ld a0, -8(fp)
mv a0, a0
L4:
   ld x9, 29016(sp)
   ld x11, 29816(sp)
   ld x12, 291616(sp)
   ld x30, 292416(sp)
   ld x31, 293216(sp)
   ld fp, 1650(sp)
   ld ra, 81650(sp)
   addi sp, sp, 80
   ret
j L7
L0:
L0:
ld x9, 8(fp)
sd x9, -8(fp)
j L2
L7:
L7:
L5:
la x31, _fib
li x9, 0
li x30, 10
sd x9, 0(sp)
sd x30, 8(sp)
jalr ra, 0(x31)
mv a0, a0
L6:
   ld x9, 8016(sp)
   ld x30, 8816(sp)
   ld x31, 81616(sp)
   ld fp, 1630(sp)
   ld ra, 81630(sp)
   addi sp, sp, 56
   ret
L8:
