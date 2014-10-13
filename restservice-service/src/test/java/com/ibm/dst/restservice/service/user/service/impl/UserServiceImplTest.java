/**
 * 
 */
package com.ibm.dst.restservice.service.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.EntityTag;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.dst.restservice.service.user.dao.UserDao;
import com.ibm.dst.restservice.service.user.model.UserModel;
import com.ibm.dst.restservice.service.user.model.UsersModel;
import com.ibm.dst.restservice.service.user.service.UserService;

/**
 * @author 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-user-service.xml"})
public class UserServiceImplTest {
	
	@Autowired
	private UserService userServiceTest = null;
	
	@Autowired
	private UserDao mockUserDao = null;
	
	@Test
	public void testGetUsers() {
		//create expect object
		UserModel user1 = new UserModel();
		user1.setId(1);
		user1.setName("test1");
		user1.setEmail("test1@cn.ibm.com");
		user1.setETag(new EntityTag(String.valueOf(user1.getId().hashCode())));
		UserModel user2 = new UserModel();
		user2.setId(2);
		user2.setName("test2");
		user2.setEmail("test2@cn.ibm.com");
		user2.setETag(new EntityTag(String.valueOf(user2.getId().hashCode())));
		List<UserModel> userList = new ArrayList<UserModel> ();
		userList.add(user1);
		userList.add(user2);
		UsersModel users = new UsersModel();
		users.setUsers(userList);
		
		EasyMock.expect(mockUserDao.getUsers()).andReturn(users);
		EasyMock.replay(mockUserDao);
		
		UsersModel actual = userServiceTest.getUsers();
		Assert.assertEquals(2, actual.getUsers().size());
		
		EasyMock.verify(mockUserDao);
	}
	
	@Test
	public void testNotNull() {
		Assert.assertNotNull(mockUserDao);
		Assert.assertNotNull(userServiceTest);
	}
	
	@Before
	public void before() {
		System.out.println("Before...");
	}
	
	@After
	public void after() {
		System.out.println("After...");
	}
	
}
