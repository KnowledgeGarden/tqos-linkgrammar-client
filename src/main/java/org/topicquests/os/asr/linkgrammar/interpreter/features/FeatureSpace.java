/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter.features;

import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;

/**
 * @author jackpark
 *
 */
public class FeatureSpace {
	private LinkGrammarClientEnvironment environment;
	private Feature [] features;
	private int cursor = 0;
	private boolean isStarted = false;
	/**
	 * 
	 */
	public FeatureSpace(LinkGrammarClientEnvironment env) {
		environment = env;
		isStarted = false;
	}

	public void startFeatures(int size) {
		System.out.println("FeatureSpace.startFeatures "+isStarted+" "+size);
		if (!isStarted) {
			features = new Feature[size];
			cursor = 0;
			isStarted = true;
		}
	}
	
	public void clearFeatures() {
		features = null;
		isStarted = false;
	}
	
	public Feature [] getFeatures() {
		return features;
	}
	
	public void addFeature(String value, int where) {
		Feature f;
		if (where == -1) {
			f = new Feature(value);
			setFeature(cursor++, f);
		} else {
			f = getFeature(where);
			f.addValue(value);
		}
			
	}
	
	public void setFeature(int where, Feature f) {
		features[where] = f;
	}
	
	public Feature getFeature(int where) {
		return features[where];
	}
}
