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
 * <p>
 * S connects subject-nouns to finite verbs:

 * 
 * "S" is a particulary problematic code; 
 */
public class S implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public S(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-S.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SFI.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SI.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SJ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SX.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-SXI.html
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
		if (left != which)
			return offset;
		boolean isFollowedByVerb = Utils.isFollowedByVerb(which, them);
		boolean isVerb = Utils.isVerb(f);
		if (!isFollowedByVerb)
			return offset;
		int right = link.getAsNumber("right").intValue();
		int delta = right-left;
		environment.logDebug("InterpretS "+which+" "+left+" "+right+" "+them[right]);
		String rightCode = link.getAsString("rightLabel");
		if (!f.hasNewFeature) {
			Feature [] siblings;
			Feature newFeature = null;
			if (code.startsWith("S")) {
				if (code.startsWith("Sp") && isVerb) { //TODO isVerb might be problematic
					//TODO
				}
			}
		}
		return offset;
	}
	

}
