package prev26lang.phase.memory;

import prev26lang.phase.asmgen.Codegen;
import prev26lang.phase.asmgen.Instr;
import prev26lang.phase.imrgen.IMR;

import java.util.Vector;

/**
 * Internal representation of stack frames and variable accesses.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class MEM {

	/**
	 * A stack frame.
	 * 
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public class Frame {

		/** The function's entry label. */
		public final Label label;

		/** The function's static depth (global functions are at depth 0). */
		public final long depth;

		/** The size of the frame. */
		public final long size;

		/** The size of the block of local variables within a frame. */
		public final long locsSize;

		/** The size of the block of (call, i.e., outgoing) arguments within a frame. */
		public final long argsSize;

		/** The register to hold the frame pointer. */
		public final Temp FP;

		/** The register to hold the return value. */
		public final Temp RV;

		/**
		 * Constructs a new frame with no temporary variables and no saved registers.
		 * 
		 * @param label    The function's entry label.
		 * @param depth    The function's static depth.
		 * @param locsSize The size of the block of local variables within a frame.
		 * @param argsSize The size of the block of (call, i.e., outgoing) arguments
		 *                 within a frame.
		 * @param size     The size of the frame.
		 */
		public Frame(Label label, long depth, long locsSize, long argsSize, long size) {
			this.label = label;
			this.depth = depth;
			this.locsSize = locsSize;
			this.argsSize = argsSize;
			this.size = size;
			this.FP = new Temp();
			this.RV = new Temp();
		}

        public Vector<Instr> codegen(IMR.Stmt stm) {
            return (new Codegen(this)).codegen(stm);
        }
        // a mapping from special temps (fp...) to their name and non-special temps to null
        public String tempMap(MEM.Temp temp) {
            if ((temp.toString()).equals(FP.toString()) || (temp.toString()).equals(Codegen.FP.toString())) {
                return "fp";
            }  else if ((temp.toString()).equals(Codegen.ZERO.toString())) {
                return "zero";
            } else if ((temp.toString()).equals(RV.toString())) {
                return "a0"; // return value
            } else if ((temp.toString()).equals(Codegen.RA.toString())) {
                return "ra"; // return address — NOT a0!
            }else if ((temp.toString()).equals(Codegen.SP.toString())) {
                return "sp";
            } else if ((temp.toString()).equals(Codegen.GP.toString())) {
                return "gp";
            } else if ((temp.toString()).equals(Codegen.TP.toString())) {
                return "tp";
            } else if ((temp.toString()).equals(Codegen.A0.toString())) {
                return "a0";
            } else if ((temp.toString()).equals(Codegen.A1.toString())) {
                return "a1";
            } else if ((temp.toString()).equals(Codegen.A2.toString())) {
                return "a2";
            } else if ((temp.toString()).equals(Codegen.A3.toString())) {
                return "a3";
            } else if ((temp.toString()).equals(Codegen.A4.toString())) {
                return "a4";
            } else if ((temp.toString()).equals(Codegen.A5.toString())) {
                return "a5";
            } else if ((temp.toString()).equals(Codegen.A6.toString())) {
                return "a6";
            }  else if ((temp.toString()).equals(Codegen.A7.toString())) {
                return "a7";
            } else if ((temp.toString()).equals(Codegen.T0.toString())) {
                return "t0";
            } else if ((temp.toString()).equals(Codegen.T1.toString())) {
                return "t1";
            } else if ((temp.toString()).equals(Codegen.T2.toString())) {
                return "t2";
            } else if ((temp.toString()).equals(Codegen.T3.toString())) {
                return "t3";
            } else if ((temp.toString()).equals(Codegen.T4.toString())) {
                return "t4";
            } else if ((temp.toString()).equals(Codegen.T5.toString())) {
                return "t5";
            } else if ((temp.toString()).equals(Codegen.T6.toString())) {
                return "t6";
            } else if ((temp.toString()).equals(Codegen.S1.toString())) {
                return "s1";
            } else if ((temp.toString()).equals(Codegen.S2.toString())) {
                return "s2";
            } else if ((temp.toString()).equals(Codegen.S3.toString())) {
                return "s3";
            } else if ((temp.toString()).equals(Codegen.S4.toString())) {
                return "s4";
            } else if ((temp.toString()).equals(Codegen.S5.toString())) {
                return "s5";
            } else if ((temp.toString()).equals(Codegen.S6.toString())) {
                return "s6";
            } else if ((temp.toString()).equals(Codegen.S7.toString())) {
                return "s7";
            } else if ((temp.toString()).equals(Codegen.S8.toString())) {
                return "s8";
            } else if ((temp.toString()).equals(Codegen.S9.toString())) {
                return "s9";
            } else if ((temp.toString()).equals(Codegen.S10.toString())) {
                return "s10";
            } else if ((temp.toString()).equals(Codegen.S11.toString())) {
                return "s11";
            }
            return null;
        }
	}

	/**
	 * Access to a variable.
	 * 
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public abstract class Access {

		/** The size of the variable. */
		public final long size;

		/**
		 * Creates new access to a variable.
		 * 
		 * @param size The size of the variable.
		 */
		public Access(long size) {
			this.size = size;
		}

	}

	/**
	 * Access to a variable at a fixed address.
	 * 
	 * (Also used for string constants.)
	 * 
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public class AbsAccess extends Access {

		/** Label denoting a fixed address. */
		public final Label label;

		/** Initial value. */
		public final String init;

		/**
		 * Constructs new absolute access.
		 * 
		 * @param size  The size of a variable.
		 * @param label Offset of a variable at an absolute address.
		 * @param init  Initial value (or {@code null}).
		 */
		public AbsAccess(long size, Label label, String init) {
			super(size);
			this.label = label;
			this.init = init;
		}

		/**
		 * Constructs new absolute access.
		 * 
		 * @param size  The size of a variable.
		 * @param label Offset of a variable at an absolute address.
		 */
		public AbsAccess(long size, Label label) {
			super(size);
			this.label = label;
			this.init = null;
		}

	}

	/**
	 * Access to a variable relative to an (unspecified) base address.
	 * 
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public class RelAccess extends Access {

		/**
		 * Offset of a variable relative to a base address, i.e., positive for
		 * parameters and record components, negative for local variables.
		 */
		public final long offset;

		/**
		 * The variable's static depth (-1 for record components, otherwise the static
		 * depth of the function within which the variable is defined).
		 */
		public final long depth;

		/**
		 * Constructs new relative access.
		 * 
		 * @param size   The size of the variable.
		 * @param offset Offset of a variable relative to a base address.
		 * @param depth  The variable's static depth (-1 for record components).
		 */
		public RelAccess(long size, long offset, long depth) {
			super(size);
			this.offset = offset;
			this.depth = depth;
		}
	}

	/**
	 * A temporary variable.
	 *
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public class Temp {

		/** The name of a temporary variable. */
		public final long temp;

		/** Counter of temporary variables. */
		private static long count = 0;

		/** Creates a new temporary variable. */
		public Temp() {
			this.temp = count;
			count++;
		}

		@Override
		public String toString() {
			return "T" + temp;
		}

	}

	/**
	 * A label.
	 * 
	 * @author bostjan.slivnik@fri.uni-lj.si
	 */
	static public class Label {

		/** The name of a label. */
		public final String name;

		/** Counter of anonymous labels. */
		private static long count = 0;

		/** Creates a new anonymous label. */
		public Label() {
			this.name = "L" + count;
			count++;
		}

		/**
		 * Creates a new named label.
		 * 
		 * @param name The name of a label.
		 */
		public Label(String name) {
			this.name = "_" + name;
		}

	}

}
