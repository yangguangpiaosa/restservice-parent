/**
 * 
 */
package com.ibm.dst.restservice.test.user.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.dst.restservice.test.core.BaseTest;
import com.ibm.dst.restservice.test.user.model.UserModel;
import com.ibm.dst.restservice.test.user.model.UsersModel;
import com.ibm.dst.restservice.test.user.xmlpojo.XMLUserModel;
import com.ibm.dst.restservice.test.user.xmlpojo.XMLUsersModel;
import com.ibm.dst.restservice.test.util.JaxbUtil;

/**
 * @author 
 *
 */
public class UserTest extends BaseTest {
	
	private static final Logger logger = Logger.getLogger(UserTest.class);
	
	@Test
	public void testGetUsers() {
		logger.info("UnitTest - UserTest :: testGetUsers");
		Response res = null;
		
		//S001_C001 && S002_C001
		//correct request,ensure the correctness of feedback
		res = super.sendRequest(RESOURCES_MAP.get("resource.user.user.users.uri"), 
									null, 
									null, 
									MediaType.APPLICATION_JSON, 
									super.HTTP_GET, 
									null);
		
		Assert.assertEquals(200, res.getStatus());
		UsersModel users = res.readEntity(UsersModel.class);
		Assert.assertNotNull(users);
		
		Map<Integer, XMLUserModel> expectMap = getExpectUserMap();
		Assert.assertEquals(2, users.getUsers().size());
		for(UserModel user : users.getUsers()) {
			Assert.assertTrue(expectMap.containsKey(user.getId()));
			Assert.assertEquals(expectMap.get(user.getId()).getId(), user.getId());
			Assert.assertEquals(expectMap.get(user.getId()).getName(), user.getName());
			Assert.assertEquals(expectMap.get(user.getId()).getEmail(), user.getEmail());
		}
		
		res.close();
	}
	
	@SuppressWarnings("serial")
	@Test
	public void testGetUserById() {
		logger.info("UnitTest - UserTest :: testGetUserById");
		Response res = null;
		
		//S001_C001 && S002_C001
		//correct request,ensure the correctness of feedback
		res = super.sendRequest(RESOURCES_MAP.get("resource.user.user.singleuser.uri"), 
									new HashMap<String, String>(){{put("id","1");}}, 
									null, 
									MediaType.APPLICATION_JSON, 
									super.HTTP_GET, 
									null);
		
		Assert.assertEquals(200, res.getStatus());
		UserModel user = res.readEntity(UserModel.class);
		Assert.assertNotNull(user);
		
		Map<Integer, XMLUserModel> expectMap = getExpectUserMap();
		Assert.assertTrue(expectMap.containsKey(user.getId()));
		Assert.assertEquals(expectMap.get(user.getId()).getId(), user.getId());
		Assert.assertEquals(expectMap.get(user.getId()).getName(), user.getName());
		Assert.assertEquals(expectMap.get(user.getId()).getEmail(), user.getEmail());
		
		res.close();
	}
	
	private Map<Integer, XMLUserModel> getExpectUserMap() {
		XMLUsersModel expectUsers = JaxbUtil.fromXMLFile(
						JaxbUtil.class.getResource("/").getPath()+"com/ibm/dst/restservice/test/expectdata/user/usersModel.xml", 
						XMLUsersModel.class);
		Map<Integer, XMLUserModel> expectMap = new HashMap<Integer, XMLUserModel> ();
		for(XMLUserModel user : expectUsers.getUsers()) {
			expectMap.put(user.getId(), user);
		}
		return expectMap;
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
