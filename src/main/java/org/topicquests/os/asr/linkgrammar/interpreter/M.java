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
public class M implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public M(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-M.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-MF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-MG.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-MJ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-MV.html ****
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-MX.html
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
