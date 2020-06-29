/**
 * 
 */
package org.topicquests.os.asr.linkgrammar;

import java.util.Map;

import org.topicquests.os.asr.linkgrammar.interpreter.LinkGrammarInterpreter;
import org.topicquests.support.RootEnvironment;
import org.topicquests.support.config.Configurator;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * @author jackpark
 *
 */
public class LinkGrammarClientEnvironment extends RootEnvironment {
	private LinkGrammarInterpreter interpreter;
	private Fetcher fetcher;
	private LinkGrammarAgent agent;

	/**
	 * 
	 */
	public LinkGrammarClientEnvironment() {
		super("config-props.xml", "logger.properties");
		interpreter = new LinkGrammarInterpreter(this);
		fetcher = new Fetcher(this);
		agent = new LinkGrammarAgent(this);
	}
	
	public LinkGrammarAgent getAgent() {
		return agent;
	}
	
	public Fetcher getFetcher() {
		return fetcher;
	}
	
	public LinkGrammarInterpreter getInterpreter() {
		return interpreter;
	}
	

	@Override
	public void shutDown() {
		System.out.println("Shutting down");

	}


}
