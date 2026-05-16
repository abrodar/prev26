.data
_g:
   .space 8
.text
_main:
   addi sp, sp, -40
   sd s0, 16(sp)
   sd ra, 24(sp)
   addi s0, sp, 40
   sd x9, 0(sp)
   sd x30, 8(sp)
L0:
   li x9, 20
   la x30, _g
   sd x9, 0(x30)
   li x9, 22
   sd x9, -8(s0)
   la x30, _g
   ld x30, 0(x30)
   ld x9, -8(s0)
   add a0, x30, x9
   mv a0, a0
L1:
   ld x9, 0(sp)
   ld x30, 8(sp)
   ld s0, 16(sp)
   ld ra, 24(sp)
   addi sp, sp, 40
   ret
L2:
