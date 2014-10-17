/**
 * 
 */
package com.ibm.dst.restservice.test.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.ibm.dst.restservice.test.util.JsonValidator;

/**
 * @author 
 *
 */
public class BaseResourceTest extends BaseTest {
	
	private static final Logger logger = Logger.getLogger(BaseResourceTest.class);
	
	//@Test
	public void testClient() {
		logger.info("UnitTest - BaseResourceTest :: testClient");
		Assert.assertNotNull(super.client);
	}
	
	//@Test
	public void testAppKey() {
		logger.info("UnitTest - BaseResourceTest :: testAppKey");
		Assert.assertTrue(true);
		Map<String, String> keyMap = new HashMap<String, String> ();
		
		keyMap.put("id", "key");
		
		Set<Map.Entry<String, String>> set = keyMap.entrySet();
		
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

            WebTarget target = client.target(super.BASE_RESOURCE_URL)
								.path(RESOURCES_MAP.get("resource.user.user.singleuser.uri"))
								.resolveTemplate("id", "1");
			Builder builder = target.request(MediaType.APPLICATION_JSON);
			builder.header("AppId", entry.getKey());
			builder.header("AppKey", entry.getValue());
			Invocation inv = builder.buildGet();
			Response res = inv.invoke();
			
			Assert.assertEquals(200, res.getStatus());
        }
	}
	
	//@Test
	public void validateJson() throws ProcessingException, IOException {
		logger.info("UnitTest - BaseResourceTest :: validateJson");
		Map<String, String> uriValidateMap = new HashMap<String, String> ();
		Map<String, Map<String, String>> pathParams = new HashMap<String, Map<String, String>> ();
		Map<String, Map<String, String>> queryParams = new HashMap<String, Map<String, String>> ();
		
		packParams(uriValidateMap, pathParams, queryParams);
		
		Set<Map.Entry<String, String>> set = uriValidateMap.entrySet();
		for(Iterator<Map.Entry<String, String>> it=set.iterator();it.hasNext();) {
			Map.Entry<String, String> validate = it.next();
			Response res = super.sendRequest(validate.getKey().split(",")[0], 
											pathParams.get(validate.getKey()), 
											queryParams.get(validate.getKey()), 
											MediaType.APPLICATION_JSON, 
											super.HTTP_GET, 
											null);
			Assert.assertEquals(200, res.getStatus());
			String responseJson = res.readEntity(String.class);
			logger.info(responseJson);
			logger.info(JsonValidator.validate(validate.getValue(), responseJson));
			Assert.assertTrue(JsonValidator.validate(validate.getValue(), responseJson).isSuccess());
		}
	}
	
	@SuppressWarnings("serial")
	private void packParams(Map<String, String> uriValidateMap, 
							Map<String, Map<String, String>> pathParams, 
							Map<String, Map<String, String>> queryParams) {
		pushParams(uriValidateMap,pathParams,queryParams,
					RESOURCES_MAP.get("resource.user.user.users.uri"),
					"user/users.json",
					new HashMap<String, String> (){},
					new HashMap<String, String> (){});
		
		pushParams(uriValidateMap,pathParams,queryParams,
					RESOURCES_MAP.get("resource.user.user.singleuser.uri"),
					"user/user.json",
					new HashMap<String, String> (){{put("id","1");}},
					new HashMap<String, String> (){});
		
	}
	
	private void pushParams(Map<String, String> uriValidateMap, 
			Map<String, Map<String, String>> pathParams, 
			Map<String, Map<String, String>> queryParams,
			String uri,
			String validJson,
			Map<String, String> pathMap,
			Map<String, String> queryMap) {
		if(uriValidateMap.containsKey(uri))
			uri = uri + "," + String.format("%.2f", Math.random());
		uriValidateMap.put(uri, validJson);
		pathParams.put(uri, pathMap);
		queryParams.put(uri, queryMap);
	}
	
	@Before
	public void before() {
		super.before();
	}
	
	@After
	public void after() {
		super.after();
	}
	
	@BeforeClass
	public static void init() {
		//load test data here, 
		//prepareData("user/user.xml");
	}
	
	@AfterClass
	public static void destory() {
		//delete test data here, 
		//deleteData("user/user.xml");
	}
	
}
