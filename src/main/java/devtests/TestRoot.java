/**
 * 
 */
package devtests;

import org.topicquests.os.asr.linkgrammar.Fetcher;
import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.LinkGrammarInterpreter;

/**
 * @author jackpark
 *
 */
public class TestRoot {
	protected LinkGrammarClientEnvironment environment;
	protected Fetcher fetcher;
	protected LinkGrammarInterpreter interpreter;

	/**
	 * 
	 */
	public TestRoot() {
		environment = new LinkGrammarClientEnvironment();
		fetcher = environment.getFetcher();
		interpreter = environment.getInterpreter();
		
	}

}
