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
public class J implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public J(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-J.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-JG.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-JQ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-JT.html
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
