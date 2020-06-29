/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter;

import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.api.ICodeInterpreter;
import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * <p>Determiner, e.g. "the"</p>
 * <p> Its left will point to a right which is the noun being determined,
 *  e.g. the black ball: left = 0; right = 2</p>
 * <p>the {@code Feature} which is the left is thus a Determiner</p>
 * <p> This can form an expectation</p>
 */
public class D implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public D(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-D.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-DD.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-DG.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-DP.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-DT.html
	 * @param which
	 * @param code
	 * @param link
	 * @param f
	 * @param them
	 * @return
	 */
	public int interpret(int which, String code, JSONObject link, Feature f, final Feature [] them) {
		int offset = 0;
		int left = link.getAsNumber("left").intValue();
		int right = link.getAsNumber("right").intValue();
		int delta = right-left;
		String rightCode = link.getAsString("rightLabel");
		Feature rf;
		if (left == which) 
			f.isDeterminer = true;
		if (code.startsWith("Ds")) {
			//this connects to a singular subject
			//TODO there are several variants of this
			rf = them[right];
			rf.isSubject = true;
		}
		else if (code.startsWith("Dmcn")) {
			//this is a numeric determiner
		}
		else if (code.startsWith("D**w")) {
			//this is a which, what, who, question
			//TODO this one's complex - save for later
		}
		//TODO
		return offset;
	}

}
