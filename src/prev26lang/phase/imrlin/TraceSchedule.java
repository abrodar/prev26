package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.Vector;

public class TraceSchedule {

    public Vector<IMR.Stmt> stmts = new Vector<>();
    public TraceSchedule(BasicBlocks b, MEM.Label entryLabel) {
        // map label -> block for quick lookup
        HashMap<MEM.Label, Vector<IMR.Stmt>> table = new HashMap<>();
        for (Vector<IMR.Stmt> block : b.blocks) {
            if (!block.isEmpty() && block.get(0) instanceof IMR.LABEL lbl) {
                // skip the done block — we'll append it manually at the end
                if (lbl.label != b.done)
                    table.put(lbl.label, block);
            }
        }

        // always start from entry block
        Vector<IMR.Stmt> entryBlock = table.get(entryLabel);
        if (entryBlock != null)
            trace(entryBlock, table, b.done);

        while (!table.isEmpty()) {
            // pick any remaining block and start a trace from it
            Vector<IMR.Stmt> block = table.values().iterator().next();
            trace(block, table, b.done);
        }

        // append done label at the end
        stmts.add(new IMR.LABEL(b.done));
        //remove jumps where the target label is the next command after jump
        int len = stmts.size();
        Vector<IMR.Stmt> cleared = new Vector<>();
        for (int i = 0; i < len; i++) {
            IMR.Stmt stmt = stmts.get(i);
            if (stmt instanceof IMR.JUMP jump && i < len - 1) {
                IMR.Stmt next_stmt = stmts.get(i + 1);
                if (next_stmt instanceof IMR.LABEL label) {
                    MEM.Label mem_label = label.label;
                    MEM.Label mem_jump = ((IMR.NAME)jump.addr).label;
                    if (mem_jump.equals(mem_label)) {
                        cleared.add(next_stmt);
                        i++;
                    } else {
                        cleared.add(stmt);
                        cleared.add(next_stmt);
                    }
                }
            } else {
                cleared.add(stmt);
            }
        }
        stmts.clear();
        stmts.addAll(cleared);
    }

    private void trace(Vector<IMR.Stmt> block, HashMap<MEM.Label, Vector<IMR.Stmt>> table, MEM.Label done) {
        while (block != null) {
            // mark block as visited by removing from table
            if (block.get(0) instanceof IMR.LABEL lbl)
                table.remove(lbl.label);

            IMR.Stmt last = block.get(block.size() - 1);

            if (last instanceof IMR.JUMP j) {
                // emit all but the last stmt, then check if we can fall through
                stmts.addAll(block.subList(0, block.size() - 1));
                MEM.Label target = null;
                if (j.addr instanceof IMR.NAME n) target = n.label;
                Vector<IMR.Stmt> next = target != null ? table.get(target) : null;
                if (next != null) {
                    // can fall through — skip the JUMP, continue trace
                    block = next;
                } else {
                    // can't fall through — emit JUMP and end trace
                    stmts.add(last);
                    block = null;
                }
            } else if (last instanceof IMR.CJUMP cj) {
                stmts.addAll(block.subList(0, block.size() - 1));
                MEM.Label falseLabel = cj.negAddr instanceof IMR.NAME n ? n.label : null;
                MEM.Label trueLabel  = cj.posAddr instanceof IMR.NAME n ? n.label : null;
                Vector<IMR.Stmt> falseBlock = falseLabel != null ? table.get(falseLabel) : null;
                Vector<IMR.Stmt> trueBlock  = trueLabel  != null ? table.get(trueLabel)  : null;

                if (falseBlock != null) {
                    // false block follows — ideal case
                    stmts.add(last);
                    block = falseBlock;
                } else if (trueBlock != null) {
                    // true block follows — flip condition so false follows
                    stmts.add(flipOper(cj));
                    block = trueBlock;
                } else {
                    // neither follows — insert a new label for false branch
                    MEM.Label ff = new MEM.Label();
                    stmts.add(new IMR.CJUMP(cj.cond, cj.posAddr, new IMR.NAME(ff)));
                    stmts.add(new IMR.LABEL(ff));
                    stmts.add(new IMR.JUMP(cj.negAddr));
                    block = null;
                }
            } else {
                // block doesn't end with jump — emit all and end trace
                stmts.addAll(block);
                block = null;
            }
        }
    }

    // flip CJUMP condition for when we need to swap true/false branches
    private IMR.CJUMP flipOper(IMR.CJUMP cj) {
        return new IMR.CJUMP(
                new IMR.UNOP(IMR.UNOP.Oper.NOT, cj.cond),
                cj.negAddr,  // old false becomes new true — jumps away
                cj.posAddr   // old true becomes new false — falls through
        );
    }
}