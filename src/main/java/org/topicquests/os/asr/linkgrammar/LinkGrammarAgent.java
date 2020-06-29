/**
 * 
 */
package org.topicquests.os.asr.linkgrammar;

import org.topicquests.os.asr.linkgrammar.interpreter.LinkGrammarInterpreter;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class LinkGrammarAgent {
	private LinkGrammarClientEnvironment environment;
	private Fetcher fetcher;
	private LinkGrammarInterpreter interpreter;
 

	/**
	 * 
	 */
	public LinkGrammarAgent(LinkGrammarClientEnvironment env) {
		environment = env;
		fetcher = environment.getFetcher();
		interpreter = environment.getInterpreter();
	}
	
	/**
	 * Parse and interpreted {@code sentence}
	 * @param sentence
	 * @return
	 */
	public IResult processSentence(String sentence) {
		IResult result = new ResultPojo();
		IResult r = fetcher.parseSentence(sentence);
		result = interpreter.interpretParseString((String)r.getResultObject());
		return result;
	}

}
