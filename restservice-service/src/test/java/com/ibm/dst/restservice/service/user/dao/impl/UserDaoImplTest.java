/**
 * 
 */
package com.ibm.dst.restservice.service.user.dao.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.dst.restservice.service.core.DaoTest;
import com.ibm.dst.restservice.service.user.dao.UserDao;
import com.ibm.dst.restservice.service.user.model.UsersModel;

/**
 * @author 
 *
 */
public class UserDaoImplTest extends DaoTest {
	
	@Autowired
	private UserDao userDaoTest = null;
	
	@Test
	public void testGetUsers() {
		UsersModel actual = userDaoTest.getUsers();
		Assert.assertEquals(2, actual.getUsers().size());
	}
	
	@Before
	public void before() throws Exception {
		System.out.println("Before...");
		//prepare test data with dbunit
		//IDataSet dataset = new FlatXmlDataSet(new File("src/test/java/com/ibm/dsc/cmms/dao/dataset-AssessItemsDaoImplTest.xml"));  
        //DatabaseOperation.CLEAN_INSERT.execute(dbconn, dataset);
	}
	
	@After
	public void after() throws Exception {
		System.out.println("after...");
	}
	
}
