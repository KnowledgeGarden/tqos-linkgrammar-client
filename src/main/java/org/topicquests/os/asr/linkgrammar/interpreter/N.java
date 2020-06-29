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
public class N implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public N(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-N.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NA.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-ND.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NI.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NJ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NM.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NN.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NO.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NR.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NS.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NT.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-NW.html
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
