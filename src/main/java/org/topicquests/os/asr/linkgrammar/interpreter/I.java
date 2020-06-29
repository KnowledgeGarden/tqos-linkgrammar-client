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
public class I implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public I(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-I.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-ID.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-IN.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-IV.html
	 * @param which
	 * @param code
	 * @param f
	 * @param them
	 * @return
	 */
	public int interpret(int which, String code, JSONObject link, Feature f, Feature [] them) {
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
		environment.logDebug("InterpretI "+which+" "+left+" "+right+" "+them[right]);
		String rightCode = link.getAsString("rightLabel");
		if (!f.hasNewFeature) {
			Feature [] siblings;
			Feature newFeature = null;
			if (code.startsWith("I") && isVerb) {
					if (delta == 1) {
						siblings = new Feature [2];
						siblings[0] = them[left];
						siblings[1] = them[right];
					} else {
						throw new RuntimeException("S missing code\n"+f.toString());
					}
					environment.logDebug("InterpretI-1\n"+siblings+ "\n"+them[right]);
					newFeature = Utils.createGeneralNewFeature(f, siblings, false);
					offset = siblings.length;
					f.myOffset = offset;
			}
		}		return offset;
	}
}
