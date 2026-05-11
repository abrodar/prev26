package prev26lang.phase.asmgen;

import prev26lang.phase.livean.Liveness;
import prev26lang.phase.livean.Node;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.Vector;

public abstract class Instr {
    public String assem;
    public abstract Vector<MEM.Temp> use();
    public abstract Vector<MEM.Temp> def();
    public abstract Targets jumps();
    public String format(MEM.Frame frame) {
        Vector<MEM.Temp> src = use();
        Vector<MEM.Temp> dst = def();
        Targets j = jumps();
        Vector<MEM.Label> jump = (j == null) ? null : j.labels;
        StringBuffer sb = new StringBuffer();
        int len = assem.length();
        for (int i = 0; i < len; i++) {
            if (assem.charAt(i) == '`') {
                switch (assem.charAt(++i)) {
                    case 's':   {
                                    int n = Character.digit(assem.charAt(++i), 10);
                                    MEM.Temp t = src.get(n);
                                    String actual = frame.tempMap(t);
                                    sb.append(actual != null ? actual : t.toString());
                                }
                                break;
                    case 'd':   {
                                    int n = Character.digit(assem.charAt(++i), 10);
                                    MEM.Temp t = dst.get(n);
                                    String actual = frame.tempMap(t);
                                    sb.append(actual != null ? actual : t.toString());
                                };
                                break;
                    case 'j':   {
                                    int n = Character.digit(assem.charAt(++i), 10);
                                    MEM.Label l = jump.get(n);
                                    sb.append(l);
                                }
                                break;
                    default: throw new Error("Bad code generation format.");
                }
            }   else {
                sb.append(assem.charAt(i));
            }
        }
        return sb.toString();
    }
    public String formatWithColor(MEM.Frame frame, HashMap<Node, String> color, Liveness ig) {
        Vector<MEM.Temp> src = use();
        Vector<MEM.Temp> dst = def();
        Targets j = jumps();
        Vector<MEM.Label> jump = (j == null) ? null : j.labels;
        StringBuffer sb = new StringBuffer();
        int len = assem.length();
        for (int i = 0; i < len; i++) {
            if (assem.charAt(i) == '`') {
                switch (assem.charAt(++i)) {
                    case 's':   {
                        int n = Character.digit(assem.charAt(++i), 10);
                        MEM.Temp t = src.get(n);
                        String actual = frame.tempMap(t);
                        sb.append(color.get(ig.tempToNode.get(actual)));
                    }
                    break;
                    case 'd':   {
                        int n = Character.digit(assem.charAt(++i), 10);
                        MEM.Temp t = dst.get(n);
                        String actual = frame.tempMap(t);
                        sb.append(color.get(ig.tempToNode.get(actual)));
                    };
                    break;
                    case 'j':   {
                        int n = Character.digit(assem.charAt(++i), 10);
                        MEM.Label l = jump.get(n);
                        sb.append(l);
                    }
                    break;
                    default: throw new Error("Bad code generation format.");
                }
            }   else {
                sb.append(assem.charAt(i));
            }
        }
        return sb.toString();
    }
}
