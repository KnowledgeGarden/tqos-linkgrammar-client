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
public class T implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public T(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TA.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TD.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TH.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TI.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TM.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TO.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TQ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TR.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TS.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TT.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TW.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TY.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-TZ.html
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
