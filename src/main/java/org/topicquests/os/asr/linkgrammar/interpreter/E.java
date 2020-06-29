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
public class E implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public E(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-E.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EA.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EB.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EC.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EE.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EI.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EL.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EN.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EP.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EQ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-ER.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-ET.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EW.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-EZ.html
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
