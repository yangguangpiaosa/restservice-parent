/**
 * 
 */
package com.ibm.dst.restservice.test.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.ibm.dst.restservice.test.core.BaseTest;

/**
 * @author 
 *
 */
public class JsonValidator {
	
	private static final String JSON_VALIDATOR_DIR = "/" + BaseTest.class.getResource("/").getPath() + "com/ibm/dst/restservice/test/validator/";
	
	public static ProcessingReport validate(String validateFile, String testString) throws ProcessingException, IOException {
		//JsonNode validator = JsonLoader.fromResource(JSON_VALIDATOR_DIR + validateFile);
		JsonNode validator = JsonLoader.fromFile(new File(JSON_VALIDATOR_DIR + validateFile));
		JsonNode test = JsonLoader.fromString(testString);
		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(validator);
        return schema.validate(test);
	}
	
}
