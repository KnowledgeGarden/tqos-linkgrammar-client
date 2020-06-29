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
 *
 */
public class C implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public C(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-C.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CC.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CO.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CP.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CQ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CV.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-CX.html
	 * @param which
	 * @param code
	 * @param link
	 * @param f
	 * @param them
	 * @return
	 */
	public int interpret(int which, String code, JSONObject link, Feature f, final Feature [] them) {
		int offset = 0;
		//TODO
		return offset;
	}
}
