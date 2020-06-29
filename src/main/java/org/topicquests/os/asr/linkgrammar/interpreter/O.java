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
 * <p>O connects transitive verbs to direct or indirect objects</p>
 * <p>If the leading word is a gerund, that favors a noun phrase</p>
 * <p>If the leading word is followed by a noun or possibly a noun phrase, 
 *  that favors a noun phrase</p>
 * <p>If the leading word is followed by an article which is followed by a noun, 
 *  that does not favor a noun phrase</p>
 * <p>If the leading word is also a verb and followed by a verb, that *seems* 
 *  to not favor a noun phrase</p> (could be wrong on that)
 * <p>Os means connects to singular noun</p>
 * <p>Ou means connects to plural noun</p>
 */
public class O implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public O(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-O.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-OD.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-OF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-OH.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-ON.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-OT.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-OX.html
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
		int right = link.getAsNumber("right").intValue();
		int delta = right-left;
		String rightCode = link.getAsString("rightLabel");

		boolean isGerund = Utils.isGerund(f);
		boolean isNoun = Utils.isNoun(f);
		Feature [] siblings;
		Feature newFeature = null;
		environment.logDebug("O "+code+" "+which+" "+isGerund+" "+link.toJSONString()+"\n"+f.toString());
		if (!f.hasNewFeature) {
			//first case is gerund
			/*if (code.equals("O") && (isGerund || isNoun) && !Utils.isFollowedByVerb(which, them)) {
				siblings = new Feature [delta+1];
				siblings[0] = f;
				if (delta == 1) {
					siblings[1] = them[which+1];
				} else {
					int len = delta+1;
					siblings = new Feature[len];
					siblings[0] = f;
					for (int i=1;i<len;i++)
						siblings[i] = them[left+i];
				}
				environment.logDebug("O-1 "+siblings.length);
				newFeature = Utils.createGeneralNewFeature(f, siblings, true);
				offset = siblings.length;
				f.myOffset = offset;
				System.out.println("NewFeature "+offset+" "+newFeature.toString());
				
			}*/
			
		}
		return offset;
	}

}
