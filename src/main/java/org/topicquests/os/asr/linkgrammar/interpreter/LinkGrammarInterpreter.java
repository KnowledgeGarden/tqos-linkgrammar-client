/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.topicquests.os.asr.linkgrammar.LinkGrammarClientEnvironment;
import org.topicquests.os.asr.linkgrammar.interpreter.api.ICodeInterpreter;
import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;
import org.topicquests.os.asr.linkgrammar.interpreter.features.FeatureSpace;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.JSONArray;

/**
 * @author jackpark
 *
 */
public class LinkGrammarInterpreter {
	private LinkGrammarClientEnvironment environment;
	private FeatureSpace features;
	private boolean isFirstParse = true;
	private int cursor = 0;
	private Set<String>unInterpretedCodes;
	protected ICodeInterpreter _A;
	protected ICodeInterpreter _B;
	protected ICodeInterpreter _C;
	protected ICodeInterpreter _D;
	protected ICodeInterpreter _E;
	protected ICodeInterpreter _F;
	protected ICodeInterpreter _G;
	protected ICodeInterpreter _H;
	protected ICodeInterpreter _I;
	protected ICodeInterpreter _J;
	protected ICodeInterpreter _K;
	protected ICodeInterpreter _L;
	protected ICodeInterpreter _M;
	protected ICodeInterpreter _N;
	protected ICodeInterpreter _O;
	protected ICodeInterpreter _P;
	protected ICodeInterpreter _Q;
	protected ICodeInterpreter _R;
	protected ICodeInterpreter _S;
	protected ICodeInterpreter _T;
	protected ICodeInterpreter _U;
	protected ICodeInterpreter _V;
	protected ICodeInterpreter _W;
	protected ICodeInterpreter _X;
	protected ICodeInterpreter _Y;
	protected ICodeInterpreter _Z;
	/**
	 * 
	 */
	public LinkGrammarInterpreter(LinkGrammarClientEnvironment env) {
		environment = env;
		features = new FeatureSpace(environment);
		unInterpretedCodes = new HashSet<String>();
		_A = new A(environment, this);
		_B = new B(environment, this);
		_C = new C(environment, this);
		_D = new D(environment, this);
		_E = new E(environment, this);
		_F = new F(environment, this);
		_G = new G(environment, this);
		_H = new H(environment, this);
		_I = new I(environment, this);
		_J = new J(environment, this);
		_K = new K(environment, this);
		_L = new L(environment, this);
		_M = new M(environment, this);
		_N = new N(environment, this);
		_O = new O(environment, this);
		_P = new P(environment, this);
		_Q = new Q(environment, this);
		_R = new R(environment, this);
		_S = new S(environment, this);
		_T = new T(environment, this);
		_U = new U(environment, this);
		_V = new V(environment, this);
		_W = new W(environment, this);
		_X = new X(environment, this);
		_Y = new Y(environment, this);
		_Z = new Z(environment, this);
	}

	/**
	 * Parse the sentence returned from the LinkGrammar Parser
	 * @param json
	 * @return
	 */
	public IResult interpretParseString(String json) {
		environment.logDebug("INTERP\n"+json);
		cursor = 0;
		isFirstParse = true;
		IResult result = new ResultPojo();
		IResult r;
		try {
			
			JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
			JSONObject jo = (JSONObject)p.parse(cleanString(json));
			environment.logDebug("TheBigKahuna\n"+jo.toJSONString()+"\n");
			JSONArray linkages = (JSONArray)jo.get("linkages");
			//Iterator<Object>itr = linkages.iterator();
			JSONObject parse;
			//First, create FeatureSpace
			int len = linkages.size();
			List<JSONObject> links;
			List<String>words;
			Iterator<JSONObject> litr;
			JSONObject lnk;
			Feature f;
			int left, right;
			//For all the links in linkages
			for (int i=0; i<len;i++){
				parse = (JSONObject)linkages.get(i);
				links = (List<JSONObject>)parse.get("links");
				if (i == 0) {
					words = (List<String>)parse.get("words");
					features.startFeatures(words.size());
					for (int j=0;j<words.size();j++)
						features.addFeature(words.get(j), -1);
				}
				
				litr = links.iterator();
				while (litr.hasNext()) {
					lnk = litr.next();
					left = lnk.getAsNumber("left").intValue();
					right = lnk.getAsNumber("right").intValue();
					f = features.getFeature(left);
					f.addLink(lnk);
					f = features.getFeature(right);
					f.addLink(lnk);
				}
			}
			//Diagnostic - print out all the features
			Feature [] ftrs = this.features.getFeatures();
			System.out.println("");
			for (int i=0;i<ftrs.length;i++)
				environment.logDebug("FTR\n"+ftrs[i].toString());
			System.out.println("");
			//Now, go process all the features
			interpretFeatures();
			environment.logDebug("UninterpretedCodes "+this.unInterpretedCodes);
		} catch (Exception e) {
			result.addErrorString(e.getMessage());
			environment.logError(e.getMessage(), e);
		}
		return result;
	}
	
	String cleanString(String json) {
		String result = json.trim();
		int where = result.indexOf('{');
		System.out.println("CLEAN "+where+" "+json.length());
		if (where > 0) {
			String temp = result.substring(0, where);
			System.out.println("CLEAN-1 "+temp);
			result = result.substring(where);
			System.out.println("CLEAN-2 "+result.length());

		}
		
		return result;
	}
	
	/**
	 * A single term from a parse
	 * This builds the {@code FeatureSpace}
	 * @param linkages
	 * @param sentenceWords
	 * @return
	 */
	public IResult interpretLinks(int which, List<JSONObject> linkages, List<String> sentenceWords) {
		IResult result = new ResultPojo();
		features.startFeatures(sentenceWords.size());
		environment.logDebug("Interpreting "+isFirstParse+"\n"+linkages+"\n"+sentenceWords);
		Iterator<String> itr = sentenceWords.iterator();
		//Tend the feature objects
		// put them into FeatureSpace
		int cursx = 0;
		while (itr.hasNext()) {
			if (which == 0) {
				features.addFeature(itr.next(), -1);
			} else {
				features.addFeature(itr.next(), cursx++);
			}
		}
		isFirstParse = false;
		cursor = 0;
		Iterator<JSONObject>  jtr = linkages.iterator();
		JSONObject jo;
		//Tend the links
		while (jtr.hasNext()) {
			jo = jtr.next();
			features.getFeature(which).addLink(jo);
		}

		
		return result;
	}
		
	void interpretFeatures() {
		//wire their edges
		Feature [] them = features.getFeatures();
		int len = them.length;
		environment.logDebug("InterpretingFeatures "+len);
		
		int lim = len-1;
		Feature f1 = them[0], f2;
		int featureCursor = 0;
		// this array could stay the same or it could shrink when some phrases are found
		Feature [] interpretedFeatures = new Feature [them.length];
		List<JSONObject> linkages;
		int offset;
		boolean isWall = false;
		//for every feature found in the parser
		//  note, this will skip some when phrases are formed
		for (int i=0; i< len; i++) {
			f1 = them[i];
			environment.logDebug("InterpretingFeatures-1 "+f1);
			isWall = isWall(f1);
			offset = interpretFeature(i, f1, them, interpretedFeatures);
			System.out.println("GOT "+f1.myOffset+" "+f1.hasNewFeature);
			if (f1.myOffset == 0 && !isWall && !f1.hasNewFeature) {
				interpretedFeatures[featureCursor++] = f1;
			} else {
				interpretedFeatures[featureCursor++] = f1.getNewFeature();
				i+=f1.myOffset;
			}
		}
		for (int i=0;i<interpretedFeatures.length;i++) {
			f1 = interpretedFeatures[i];
			if (f1 != null)
				environment.logDebug("XXX "+f1.toString());
		}
	}
	boolean isWall(Feature f) {
		List<String> vals = f.value;
		String s = vals.get(0);
		return(s.indexOf("-WALL") > 0);
	}

	
	int interpretFeature(int which, Feature f, final Feature [] them, Feature [] interpretedFeatures) {
		environment.logDebug("Interpreter.interpretLink "+which+" "+f.toString());
		int offset = 0;
		//List<String> values = f.value;
		List<JSONObject> links = f.links;
		JSONObject jo;
		Iterator<JSONObject> itr = links.iterator();
		while (itr.hasNext()) {
			jo = itr.next();
			offset = interpretFeatureLink(which, jo,f, them, interpretedFeatures);
		}
		
		return offset;
	}
	
	int interpretFeatureLink(int which, JSONObject link, Feature f, final Feature [] them, Feature [] interpretedFeatures) {
		int offset = 0;
		String f1 = link.getAsString("leftLabel");
		String f2 = link.getAsString("label");
		String f3 = link.getAsString("rightLabel");
		String code = f1;
		if (f1.startsWith("W")) {
			offset = _W.interpret(which, code, link, f, them);
		} else if (f1.startsWith("hW")) {
			offset = _W.interpret(which, code, link, f, them);
		} else if (f1.startsWith("A")) {
			offset = _A.interpret(which, code, link, f, them);
		} else if (f1.startsWith("@A")) {
			offset = _A.interpret(which, code, link, f, them);
		} else if (f1.startsWith("S")) {
			offset = _S.interpret(which, code, link, f, them);
		} else if (f1.startsWith("O")) {
			offset = _O.interpret(which, code, link, f, them);
		} else if (f1.startsWith("M")) {
			offset = _M.interpret(which, code, link, f, them);
		} else if (f1.startsWith("B")) {
			offset = _B.interpret(which, code, link, f, them);
		} else if (f1.startsWith("R")) {
			offset = _R.interpret(which, code, link, f, them);
		} else if (f1.startsWith("C")) {
			offset = _C.interpret(which, code, link, f, them);
		} else if (f1.startsWith("dC")) {
			offset = _C.interpret(which, code, link, f, them);
		} else if (f1.startsWith("T")) {
			offset = _T.interpret(which, code, link, f, them);
		} else if (f1.startsWith("X")) {
			offset = _X.interpret(which, code, link, f, them);
		} else if (f1.startsWith("P")) {
			offset = _P.interpret(which, code, link, f, them);
		} else if (f1.startsWith("E")) {
			offset = _E.interpret(which, code, link, f, them);
		} else if (f1.startsWith("N")) {
			offset = _N.interpret(which, code, link, f, them);
		} else if (f1.startsWith("J")) {
			offset = _J.interpret(which, code, link, f, them);
		} else if (f1.startsWith("F")) {
			offset = _F.interpret(which, code, link, f, them);
		} else if (f1.startsWith("G")) {
			offset = _G.interpret(which, code, link, f, them);
		} else if (f1.startsWith("H")) {
			offset = _H.interpret(which, code, link, f, them);
		} else if (f1.startsWith("I")) {
			offset = _I.interpret(which, code, link, f, them);
		} else if (f1.startsWith("K")) {
			offset = _K.interpret(which, code, link, f, them);
		} else if (f1.startsWith("D")) {
			offset = _D.interpret(which, code, link, f, them);
		} else if (f1.startsWith("L")) {
			offset = _L.interpret(which, code, link, f, them);
		} else if (f1.startsWith("Q")) {
			offset = _Q.interpret(which, code, link, f, them);
		} else if (f1.startsWith("U")) {
			offset = _U.interpret(which, code, link, f, them);
		} else if (f1.startsWith("V")) {
			offset = _V.interpret(which, code, link, f, them);
		} else if (f1.startsWith("W")) {
			offset = _W.interpret(which, code, link, f, them);
		} else if (f1.startsWith("X")) {
			offset = _X.interpret(which, code, link, f, them);
		} else if (f1.startsWith("Y")) {
			offset = _Y.interpret(which, code, link, f, them);
		} else if (f1.startsWith("Z")) {
			offset = _Z.interpret(which, code, link, f, them);
		} else {
			this.unInterpretedCodes.add(f1);
		}
					
	
		return offset;
	}
	

	













	

}
