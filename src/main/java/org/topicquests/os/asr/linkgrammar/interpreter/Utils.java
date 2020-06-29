/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter;

import java.util.Iterator;
import java.util.List;

import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class Utils {

	/**
	 * 
	 */
	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This test only says that one sense is a verb
	 * @param f
	 * @return
	 */
	public static boolean isVerb(Feature f) {
		List<String> values = f.value;
		Iterator<String>itr = values.iterator();
		String val;
		while (itr.hasNext()) {
			val = itr.next();
			if (val.endsWith(".v"))
				return true;
		}
		return false;
	}
	/**
	 * This may not be strong enough: it assumes the next word is, in fact, a possible verb<br/>
	 * Another weakness is that the next word could also be a noun and this really is a noun phrase
	 * @param which
	 * @param them
	 * @return
	 */
	public static boolean isFollowedByVerb(int which, final Feature [] them) {
		boolean result = false;
		int len = them.length;
		Feature f;
		if (which+1 < len) {
			f = them[which+1];
			List<String> values = f.value;
			Iterator<String>itr = values.iterator();
			String val;
			while (itr.hasNext()) {
				val = itr.next();
				if (val.endsWith(".v"))
					return true;
			}
		}
		return result;
	}
	
	/**
	 * This test only says that one sense is a gerund
	 * @param f
	 * @return
	 */
	public static boolean isGerund(Feature f) {
		List<String> values = f.value;
		Iterator<String>itr = values.iterator();
		String val;
		while (itr.hasNext()) {
			val = itr.next();
			if (val.endsWith(".g"))
				return true;
		}
		return false;
	}
	
	/**
	 * This test only says that one sense is a noun
	 * @param f
	 * @return
	 */
	public static boolean isNoun(Feature f) {
		List<String> values = f.value;
		Iterator<String>itr = values.iterator();
		String val;
		while (itr.hasNext()) {
			val = itr.next();
			if (val.indexOf(".n") > -1)
				return true;
		}
		return false;
	}

	/**
	 * This test only says that one sense is a conjunction
	 * @param f
	 * @return
	 */
	public static boolean isConjunction(Feature f) {
		List<String> values = f.value;
		Iterator<String>itr = values.iterator();
		String val;
		while (itr.hasNext()) {
			val = itr.next();
			if (val.indexOf(".j") > -1)
				return true;
		}
		return false;
	}

	/**
	 * This test only says that one sense is an adjective
	 * @param f
	 * @return
	 */
	public static boolean isAdjective(Feature f) {
		List<String> values = f.value;
		Iterator<String>itr = values.iterator();
		String val;
		while (itr.hasNext()) {
			val = itr.next();
			if (val.indexOf(".a") > -1)
				return true;
		}
		return false;
	}

	/**
	 * Create a new {@link Feature} which is either a noun or verb phrase
	 * @param f
	 * @param siblings
	 * @param isNoun
	 * @return
	 */
	public static Feature createGeneralNewFeature(Feature f, Feature [] siblings, boolean isNoun) {
		System.out.println("CreateNewGenFeature "+f.toString());
		Feature result = null;
		StringBuilder buf = new StringBuilder();
		buf.append(Utils.grabTerm(f)+" ");
		int len = siblings.length;
		System.out.println("CreateNewGenFeature-1 "+len);
		for (int x = 0;x<len;x++)
			System.out.println("CreateNewGenFeature-2 "+siblings[x]);

		for (int i=0;i<len;i++) {
			buf.append(Utils.grabTerm(siblings[i])+" ");
		}
		result = f.createNewFeature(buf.toString().trim());
		if (isNoun)
			result.isNoun = true;
		else
			result.isVerb = true;
		return result;
	}

	/**
	 * Just return the first value in this {@link Feature}
	 * @param f
	 * @return
	 */
	public static String grabTerm(Feature f) {
		System.out.println("GrabTerm "+f.toString());
		List<String>vals = f.value;
		return vals.get(0);
	}

	/**
	 * Scan links for one with the same code, same begin, but larger end
	 * @param f
	 * @param code
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean hasLongerSpan(Feature f, String code, int begin, int end) {
		boolean result = false;
		List<JSONObject>links = f.links;
		JSONObject jo;
		int left = 0;
		int right = 0;
		String leftCode;
		Iterator<JSONObject>itr = links.iterator();
		while (itr.hasNext()) {
			jo = itr.next();
			System.out.println("HLS "+code+" "+begin+" "+end+" "+jo.toJSONString());
			leftCode = jo.getAsString("leftLabel");
			if (code.equals(leftCode)) {
				left = jo.getAsNumber("left").intValue();
				if (left == begin) {
					right = jo.getAsNumber("right").intValue();
					if (right > end)
						return true;
				}
			}
		}
		
		return result;
	}


}
