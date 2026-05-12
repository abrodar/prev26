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
		public Label label;

		/** The function's static depth (global functions are at depth 0). */
		public long depth;

		/** The size of the frame. */
		public long size;

		/** The size of the block of local variables within a frame. */
		public long locsSize;

		/** The size of the block of (call, i.e., outgoing) arguments within a frame. */
		public long argsSize;
        /** The size of the block of temporaries within a frame (by AB) */
        public long tempsSize;
		/** The register to hold the frame pointer. */
		public Temp FP;

		/** The register to hold the return value. */
		public Temp RV;

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
            // initially zero, then regall can increase temps size
            this.tempsSize = 0;
		}

        public Vector<Instr> codegen(IMR.Stmt stm) {
            return (new Codegen(this)).codegen(stm);
        }

        // a mapping from special temps (fp...) to their name and non-special temps to null
        public String tempMap(MEM.Temp temp) {
            if ((temp.toString()).equals(FP.toString()) || (temp.toString()).equals(Codegen.FP.toString())) {
                return "fp";
            } else if ((temp.toString()).equals(RV.toString()) || (temp.toString()).equals(Codegen.A0.toString())) {
                return "a0"; // return value
            } else if ((temp.toString()).equals(Codegen.RA.toString())) {
                return "ra"; // return address — NOT a0!
            }else if ((temp.toString()).equals(Codegen.SP.toString())) {
                return "sp";
            } else if ((temp.toString()).equals(Codegen.ZERO.toString())) {
                return "zero";
            } else if ((temp.toString()).equals(Codegen.GP.toString())) {
                return "gp";
            } else if ((temp.toString()).equals(Codegen.TP.toString())) {
                return "tp";
            } else if ((temp.toString()).equals(Codegen.X5.toString())) {
                return "x5";
            } else if ((temp.toString()).equals(Codegen.X6.toString())) {
                return "x6";
            } else if ((temp.toString()).equals(Codegen.X7.toString())) {
                return "x7";
            } else if ((temp.toString()).equals(Codegen.X9.toString())) {
                return "x9";
            } else if ((temp.toString()).equals(Codegen.X11.toString())) {
                return "x11";
            } else if ((temp.toString()).equals(Codegen.X12.toString())) {
                return "x12";
            } else if ((temp.toString()).equals(Codegen.X13.toString())) {
                return "x13";
            } else if ((temp.toString()).equals(Codegen.X14.toString())) {
                return "x14";
            } else if ((temp.toString()).equals(Codegen.X15.toString())) {
                return "x15";
            } else if ((temp.toString()).equals(Codegen.X16.toString())) {
                return "x16";
            } else if ((temp.toString()).equals(Codegen.X17.toString())) {
                return "x17";
            } else if ((temp.toString()).equals(Codegen.X18.toString())) {
                return "x18";
            } else if ((temp.toString()).equals(Codegen.X19.toString())) {
                return "x19";
            } else if ((temp.toString()).equals(Codegen.X20.toString())) {
                return "x20";
            } else if ((temp.toString()).equals(Codegen.X21.toString())) {
                return "x21";
            } else if ((temp.toString()).equals(Codegen.X22.toString())) {
                return "x22";
            } else if ((temp.toString()).equals(Codegen.X23.toString())) {
                return "x23";
            } else if ((temp.toString()).equals(Codegen.X24.toString())) {
                return "x24";
            } else if ((temp.toString()).equals(Codegen.X25.toString())) {
                return "x25";
            } else if ((temp.toString()).equals(Codegen.X26.toString())) {
                return "x26";
            } else if ((temp.toString()).equals(Codegen.X27.toString())) {
                return "x27";
            } else if ((temp.toString()).equals(Codegen.X28.toString())) {
                return "x28";
            } else if ((temp.toString()).equals(Codegen.X29.toString())) {
                return "x29";
            } else if ((temp.toString()).equals(Codegen.X30.toString())) {
                return "x30";
            } else if ((temp.toString()).equals(Codegen.X31.toString())) {
                return "x31";
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
