package org.topicquests.os.asr.linkgrammar.interpreter;

import java.util.*;
import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.api.ICodeInterpreter;
import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * 
 * @author jackpark
 * <p>
 * AN connects noun-modifiers to nouns.
 * Noun modifiers therefore always connect in parallel, 
 * rather than serially </p>
 */
public class A implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	public A(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
	}
	
	/**
	 * Here because a leftLabel on a link started with "A" or nearby
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-A.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AA.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AJ.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AL.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AM.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AN.html ****
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-AZ.html
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
		//look for disqualifiers
		if (left != which)
			return offset;
		//block determiners
		if (left == which && f.isDeterminer)
			return offset;
		boolean followedByVerb = Utils.isFollowedByVerb(which, them);
		// followedByVerb should disqualify this
		if (followedByVerb)
			return offset;
		boolean isConjunction = Utils.isConjunction(f);
		if (isConjunction)
			return offset;
		//process the term
		boolean isVerb = Utils.isVerb(f);
		boolean isNoun = Utils.isNoun(f);
		boolean isSubject = f.isSubject;

		int right = link.getAsNumber("right").intValue();
		int delta = right-left;
		String rightCode = link.getAsString("rightLabel");

		boolean hasLongerSpan = Utils.hasLongerSpan(f, rightCode, left, right);
		if (hasLongerSpan)
			return offset; // let's wait till next pass
		//that test has to do with injecting links to the left and the right
		//which can tell a feature which is a conjunction it's an AN
		//TODO fix that
		environment.logDebug("A "+code+" "+which+" "+hasLongerSpan+" "+link.toJSONString()+"\n"+f.toString());
		if (!f.hasNewFeature) {
			Feature newFeature = null;
			Feature [] siblings = null;
			if (code.indexOf("AN") > -1) {
				//This is about connected nouns
				// but there is an issue: LG does not pick up the noun sense of some words
				//if (!followedByVerb && !isVerb) {
					//This is non-trivial, especially if this is the
					// first pass. That's because this could be a noun
					// early in the sentence with a verb between it and the
					// final noun
					System.out.println("AN "+delta+" "+isVerb+" "+followedByVerb+" "+link.toJSONString());
					siblings = getSiblings(which+1, right, them);
				//} else {
					//nothing else to do (it says here in the fine print)
				//	return offset;
				//}
			} else if (code.equals("A")) {
				//this might start with an adjective or adjectival phrase
				
			}
			if (siblings != null) {
				newFeature = createNewFeature(f, siblings);
				offset = delta;
				f.myOffset = offset;
			}
		}
		//TODO
		return offset;
	}
	
	
	Feature createNewFeature(Feature f, Feature [] siblings) {
		Feature result = null;
		StringBuilder buf = new StringBuilder();
		buf.append(grabNoun(f)+" ");
		int len = siblings.length;
		for (int i=0;i<len;i++) {
			buf.append(grabNoun(siblings[i])+" ");
		}
		result = f.createNewFeature(buf.toString().trim());
		result.isNoun = true;
		System.out.println("NewFeature "+result.toString());
		return result;
	}
	
	String grabNoun(Feature f) {
		List<String>vals = f.value;
		int len = vals.size();
		if (len == 1)
			return vals.get(0);
		else {
			String s = pluckNoun(vals);
			if (s == null)
				s = vals.get(0); //hack
			return s;	
		}
	}
	String pluckNoun(List<String> vals) {
		String result = null;
		int len = vals.size();
		String s;
		for (int i=0;i<len;i++) {
			s = vals.get(i);
			if (s.indexOf(".n") > -1)
				return s;
		}
		return result;
	}
	Feature [] getSiblings(int start, int end, final Feature [] them) {
		int len = end-start+1;
		Feature [] result = new Feature[len];
		for (int i=0;i<len;i++)
			result[i] = them[start++];
		return result;
	}

	

}
