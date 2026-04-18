package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;

import java.util.Vector;

// BasicBlocks groups statements into sequences of straight-line code.
public class BasicBlocks {
    public Vector<IMR.STMTS> blocks;
    public IMR.LABEL done;
    public BasicBlocks(IMR.STMTS stms) {}
}
