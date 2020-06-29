/**
 * 
 */
package devtests;

import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class InterpretTest1 extends TestRoot {
	private final String
		S1 = "CO2 causes climate change",
		S2 = "Climate change is caused by CO2",
		S4 = "Some scientists believe that CO2 causes climate change",
		S3 = 
			"The pandemic of obesity, type 2 diabetes mellitus (T2DM) and nonalcoholic fatty liver disease (NAFLD) has frequently been associated with dietary intake of saturated fats (1) and specifically with dietary palm oil (PO) (2).",
		S5 =
			"In this Journal of General Physiology Milestone, the basic principles of voltage sensing and gating currents are presented, followed by a historical description of the recording of gating currents.",
		S6 = "Sleep is essential for both cognition and maintenance of healthy brain function.",
		S7 = "Many membrane proteins sense the voltage across the membrane where they are inserted, and their function is affected by voltage changes.",
		S8 = "The voltage sensor consists of charges or dipoles that move in response to changes in the electric field, and their movement produces an electric current that has been called gating current.",
		S9 = "In the case of voltage-gated ion channels, the kinetic and steady-state properties of the gating charges provide information of conformational changes between closed states that are not visible when observing ionic currents only.",
		S10 = "The results of gating current recordings are then discussed in the context of structural changes in voltage-dependent membrane proteins and how these studies have provided new insights on gating mechanisms.",
		S11 = "Because humans consume food, they can survive",
		S12 = "colorless green ideas sleep furiously.";
			
	/**
	 * 
	 */
	public InterpretTest1() {
		IResult r = fetcher.parseSentence(S12);
		System.out.println("AAA "+r.getErrorString());
		r  = interpreter.interpretParseString((String)r.getResultObject());
		System.out.println("B "+r.getErrorString());
		environment.shutDown();
		System.exit(0);
	}

}
