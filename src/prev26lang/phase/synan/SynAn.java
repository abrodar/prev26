package prev26lang.phase.synan;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import prev26lang.common.report.*;
import prev26lang.phase.*;
import prev26lang.phase.lexan.*;

/**
 * Syntax analysis phase.
 */
public class SynAn extends Phase {

	/** The parse tree. */
	public static Prev26Parser.SourceContext tree;

	/** The ANTLR parser that actually performs syntax analysis. */
	public final Prev26Parser parser;

	/**
	 * Phase construction: sets up logging and the ANTLR lexer and parser.
	 * 
	 * @param lexan The lexical analyzer.
	 */
	public SynAn(final LexAn lexan) {
		super("synan");
		parser = new Prev26Parser(new CommonTokenStream(lexan.lexer));
		parser.removeErrorListeners(); // remove default error listeners
		parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg,
                                    RecognitionException e)
            {
                // dobi pravilno lokacijo že od pregledovalnika
                String detailed = "\n\nThere is a mistake in the code at line " + line +
                        ", column " + charPositionInLine +
                        ", near '" + ((LexAn.LocLogToken) offendingSymbol).getText() + "'.\nThe compiler did not expect this here.";
				Location location = new Location(line, charPositionInLine);
                throw new Report.Error(location, detailed);
			}
		});
	}

	/**
	 * Logs a parse tree.
	 * 
	 * @param tree The parse tree to be logged.
	 */
	public void log(final ParseTree tree) {
		if (xmlLogger == null)
			return;
		if (tree instanceof TerminalNodeImpl) {
			final TerminalNodeImpl node = (TerminalNodeImpl) tree;
			((LexAn.LocLogToken) (node.getPayload())).log(xmlLogger);
		}
		if (tree instanceof ParserRuleContext) {
			final ParserRuleContext node = (ParserRuleContext) tree;
			xmlLogger.begElement("node");
			xmlLogger.addAttribute("label", Prev26Parser.ruleNames[node.getRuleIndex()]);
			final int numChildren = node.getChildCount();
			for (int i = 0; i < numChildren; i++)
				log(node.getChild(i));
			xmlLogger.endElement();
		}
	}

}
