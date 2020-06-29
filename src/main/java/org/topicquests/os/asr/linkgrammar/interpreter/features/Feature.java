/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter.features;

import java.util.*;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * <p>A {@code Feature} begins life as an individual entity
 * parsed by LinkGrammar - a word, a punctuation</p>
 * <p>It serves as a blackboard for the many times it may pass
 * through an interpreter. In so doing, if the link structure
 * means this feature is the beginning of a Phrase, e.g. with AN,
 * Then the interpreter may reach out and collect the other features
 * as a shell for a new Feature which is for a phrase.</p>
 * <p>That phrase will replace those individuals for which it
 * now owns</p>
 * 
 */
public class Feature {
	public String id;
	public String label;
	/**
	 * There is an issue with value; it can be different at times
	 * e.g. cause.n, cause.v
	 */
	public List<String> value;
	/**
	 * Lots of possible link objects
	 */
	public List<JSONObject> links;
	public boolean isCapatilizedWord = false;
	public boolean isNoun = false;
	public boolean isVerb = false;
	public boolean isDeterminer = false;
	public boolean isGerund = false;
	public boolean isAdjective = false;
	public boolean isAdverb = false;
	public boolean isSubject = false;
	public boolean isObject = false;
	public boolean isPunctuation = false;
	public boolean isPassive = false;
	public boolean hasNewFeature = false;
	public int myOffset = 0;
	private Feature newFeature;

	/**
	 * 
	 */
	public Feature() {
		id = UUID.randomUUID().toString();
	}

	public Feature(String value) {
		id = UUID.randomUUID().toString();
		addValue(value);
	}
	
	public void addValue(String v) {
		if (value == null) value = new ArrayList<String>();
		if (!value.contains(v))
			value.add(v);
	}
	
	public void addLink(JSONObject l) {
		if (links == null) links = new ArrayList<JSONObject>();
		if (!links.contains(l))
			links.add(l);
	}
	
	public List<JSONObject> getLinks() {
		return links;
	}
	
	public String toString() {
		String result = "";
		if (isNoun)
			result += "IsNoun ";
		if (isVerb)
			result += "IsVerb ";
		if (isPassive)
			result += "IsPassive ";
		if (isDeterminer)
			result += "IsDeterminer ";
		if (isAdverb)
			result += "IsAdverb ";
		if (isAdjective)
			result += "IsAdjective ";
		if (isPunctuation)
			result += "IsPunctuation ";
		if (isSubject)
			result += "IsSubject ";
		if (isObject)
			result += "IsObject ";

		result += value+" "+links;
		return result;
	}
	
	/**
	 * If we find that this feature must be replaced by a new one
	 * such as a phrase, create it here
	 * @param newValue composed of the feature values from others
	 * @return return here for further computations e.g. POS
	 */
	public Feature createNewFeature(String newValue) {
		newFeature = new Feature(newValue);
		hasNewFeature = true;
		return newFeature;
	}
	
	public Feature getNewFeature() {
		return newFeature;
	}
	
}
