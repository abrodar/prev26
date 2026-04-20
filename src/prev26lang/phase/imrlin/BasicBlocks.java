package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.Vector;

// BasicBlocks groups statements into sequences of straight-line code.
public class BasicBlocks {
    public Vector<Vector<IMR.Stmt>> blocks = new Vector<>();
    public MEM.Label done;

    public BasicBlocks(Vector<IMR.Stmt> stmts) {
        done = new MEM.Label();
        Vector<IMR.Stmt> current = null;

        for (IMR.Stmt s : stmts) {
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

        // add done label as empty final block
        Vector<IMR.Stmt> doneBlock = new Vector<>();
        doneBlock.add(new IMR.LABEL(done));
        blocks.add(doneBlock);
    }
}