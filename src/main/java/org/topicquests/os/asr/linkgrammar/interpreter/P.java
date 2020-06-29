/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter;

import java.util.*;
import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.api.ICodeInterpreter;
import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * <p>P is used to link forms of the verb "be" to various words that can be its complements: 
 * prepositions, adjectives, and passive and progressive participles.</p>
 * <p>Pp is used to attach forms of "be" to prepositions. Prepositions thus have "Pp-" 
 * directly disjoined with other connectors used for attaching prepositional phrases 
 * to things like Mp- (used for phrases modifying nouns), 
 * MVp- (used for phrases modifying verbs), CO+ (used for openers), and so on.</p>
 * <p>Pg connects verbs that take present participles with present participles.</p>
 * <p>Pv is used to connect forms of "be" to passive participles</p>
 * <p>Pa connects certain verbs to predicative adjectives</p>
 */
public class P implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public P(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-P.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-PF.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-PH.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-PP.html
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
		int right = link.getAsNumber("right").intValue();
		int delta = right-left;
		Feature [] siblings;
		Feature newFeature = null;
		System.out.println("Pa "+code+" "+which+" "+link.toJSONString());
		if (!f.hasNewFeature) {
			if (code.equals("Pv")) {
				siblings = gatherSiblings(which, right, them);
				if (siblings != null) {
					newFeature = this.createNewFeature(f, siblings);
					offset = siblings.length;
					f.myOffset = offset;
					System.out.println("NewFeature "+offset+" "+newFeature.toString());

				}
			}
		}
		
		return offset;
	}
	
	Feature [] gatherSiblings(int which, int right, final Feature [] them) {
		Feature [] temp = new Feature[12];
		Feature [] result = null;
		int delta = right - which;
		System.out.println("Pb "+which+" "+right);
		int cursor = 0;
		int pointer = which;
		Feature next;
		int counter = 0;
		System.out.println("Pc "+delta);
		boolean hasNext = true;
		if (delta == 1) {
			while (hasNext) {
				next = them[pointer+1];
				hasNext = hasNext(pointer, next);
				if (hasNext || counter > 0) {
					temp[cursor++] = next;
					counter++;
					pointer++;
				}
			}
		}
		if (counter > 0) {
			result = new Feature[counter];
			for (int i=0;i<counter;i++)
				result[i] = temp[i];
		}
		return result;
	}
	
	boolean hasNext(int where, Feature f) {
		System.out.println("P-0 "+where+" "+f.toString());
		boolean result = false;
		List<JSONObject> links = f.links;
		JSONObject jo;
		String code;
		int left, right;
		Iterator<JSONObject>itr = links.iterator();
		while (itr.hasNext()) {
			jo = itr.next();
			code = jo.getAsString("leftLabel");
			System.out.println("P-1 "+code+" "+where+" "+jo.toJSONString());
			if (code.equalsIgnoreCase("pv")) { //TODO that may be too strict
				left = jo.getAsNumber("left").intValue();
				right = jo.getAsNumber("right").intValue();
				System.out.println("P-2 "+left+" "+right);

				if (right > where)
					return true;
			}
		}
		return result;
	}
	
	Feature createNewFeature(Feature f, Feature [] siblings) {
		Feature result = null;
		StringBuilder buf = new StringBuilder();
		buf.append(grabVerb(f)+" ");
		int len = siblings.length;
		for (int i=0;i<len;i++) {
			buf.append(grabVerb(siblings[i])+" ");
		}
		result = f.createNewFeature(buf.toString().trim());
		result.isVerb = true;
		return result;
	}
	
	String grabVerb(Feature f) {
		List<String>vals = f.value;
		int len = vals.size();
		if (len == 1)
			return vals.get(0);
		else {
			String s = pluckVerb(vals);
			if (s == null)
				s = vals.get(0); //hack
			return s;	
		}
	}
	String pluckVerb(List<String> vals) {
		String result = null;
		int len = vals.size();
		String s;
		for (int i=0;i<len;i++) {
			s = vals.get(i);
			if (s.indexOf(".v") > -1)
				return s;
		}
		return result;
	}
	
}
