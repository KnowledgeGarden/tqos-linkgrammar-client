/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter;

import java.util.Iterator;
import java.util.List;

import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.api.ICodeInterpreter;
import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * <p>G & G+ connects proper nouns together in series.</p>
 * <p>GN is a link, always carrying a cost of 2, used in expressions 
 * where proper nouns are introduced by a common noun, with or
 * without a determiner</p>
 * <p>Note: the documentation says that G is serial, one to the next;
 * I am observing that it connects first to last. So, the code needs
 * to all that, if the delta is > 1, use that as the full span;
 * otherwise, walk nextLinks.</p>
 */
public class G implements ICodeInterpreter {
	private LinkGrammarClientEnvironment environment;
	private LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public G(LinkGrammarClientEnvironment env, LinkGrammarInterpreter interp) {
		environment = env;
		interpreter = interp;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-G.html
	 * @see https://www.abisource.com/projects/link-grammar/dict/section-GN.html
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

		Feature [] siblings;
		Feature newFeature = null;
		environment.logDebug("G "+code+" "+which+" "+link.toJSONString());
		if (!f.hasNewFeature) {
			if (code.equals("G") || code.equals("G+")) {
				if (delta == 1) {
					siblings = gatherSiblings(which, right, f, them);
				}
				else 
					siblings = grabSiblings(which, left, right, f, them);
				if (siblings != null) {
					newFeature = Utils.createGeneralNewFeature(f, siblings, true);
					offset = siblings.length;
					f.myOffset = offset;
					System.out.println("NewFeature "+offset+" "+newFeature.toString());

				}
			}
		} //TODO add GN
		
		return offset;
	}
	
	Feature [] grabSiblings(int which, int left, int right, Feature f, final Feature [] them) {
		int len = right-left+1;
		Feature [] result = new Feature[len];
		result[0]=f;
		Feature x;
		int cursx = left;
		for (int i = 1;i<len;i++) {
			cursx = left+i;
			x = them[cursx];
			result[i]=x;
			System.out.println("OGRF "+which+" "+cursx+" "+right+" "+x.toString());
		}
		return result;
	}
	
	Feature [] gatherSiblings(int which, int right, Feature f, final Feature [] them) {
		Feature [] temp = new Feature[12];
		temp[0] = f;
		Feature [] result = null;
		int delta = right - which;
		System.out.println("Pb "+which+" "+right);
		int cursor = 1;
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
			if (code.equals("G") || code.equals("G+")) {
				left = jo.getAsNumber("left").intValue();
				right = jo.getAsNumber("right").intValue();
				System.out.println("P-2 "+left+" "+right);

				if (right > where)
					return true;
			}
		}
		return result;
	}
	
	

}
