/**
 * 
 */
package devtests;

import java.util.*;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class FetcherTest2 extends TestRoot {
	private final String
		S1 = "CO2 causes climate change",
		S2 = "Climate change is caused by carbon dioxide",
		S3 = "Some scientists believe that climate change is caused by CO2";
	/**
	 * 
	 */
	public FetcherTest2() {
		IResult r;
		List<String> sentences = new ArrayList<String>();
		sentences.add(S1);
		sentences.add(S2);
		sentences.add(S3);
		r = fetcher.processSentences(sentences);
		System.out.println("B "+r.getResultObject());
		environment.shutDown();
		System.exit(0);
	}

}
