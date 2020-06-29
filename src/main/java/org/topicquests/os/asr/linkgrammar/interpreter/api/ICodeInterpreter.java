/**
 * 
 */
package org.topicquests.os.asr.linkgrammar.interpreter.api;

import org.topicquests.os.asr.linkgrammar.interpreter.features.Feature;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public interface ICodeInterpreter {

	int interpret(int which, String code, JSONObject link, Feature f, final Feature [] them);

}
