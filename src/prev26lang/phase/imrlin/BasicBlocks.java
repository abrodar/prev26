package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.Vector;

// BasicBlocks groups statements into sequences of straight-line code.
public class BasicBlocks {
    public Vector<Vector<IMR.Stmt>> blocks = new Vector<>();
    public MEM.Label done;

    public BasicBlocks(Vector<IMR.Stmt> stmts, MEM.Label exitLabel) {
        done = exitLabel; // exit / done label goes at the start of epilogue, so that last block ends with jump(name done)
        Vector<IMR.Stmt> current = null;

        for (IMR.Stmt s : stmts) {
            // NEW: a label starts a new block. If we're mid-block (fall-through
            // into this label), close the current block with an explicit jump to
            // the label so control flow is preserved, then start fresh.
            if (s instanceof IMR.LABEL && current != null) {
                current.add(new IMR.JUMP(new IMR.NAME(((IMR.LABEL) s).label)));
                blocks.add(current);
                current = null;
            }
            if (current == null) {
                // start new block
                current = new Vector<>();
                // every block must start with a LABEL
                if (!(s instanceof IMR.LABEL)) {
                    current.add(new IMR.LABEL(new MEM.Label()));
                }
            }
            current.add(s);

            // block ends at JUMP or CJUMP
            if (s instanceof IMR.JUMP || s instanceof IMR.CJUMP) {
                blocks.add(current);
                current = null;
            }
        }

        // handle any remaining stmts that didn't end with a jump
        if (current != null) {
            current.add(new IMR.JUMP(new IMR.NAME(done)));
            blocks.add(current);
        }

        // add done label as empty final block; NOT NEEDED, WE ALREADY ADD IT IN TRACESCHEDULE.JAVA
        /*
        Vector<IMR.Stmt> doneBlock = new Vector<>();
        doneBlock.add(new IMR.LABEL(done));
        blocks.add(doneBlock);*/
    }
}