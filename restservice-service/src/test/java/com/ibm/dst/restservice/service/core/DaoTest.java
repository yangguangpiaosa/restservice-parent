/**
 * 
 */
package com.ibm.dst.restservice.service.core;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 
 *
 */
@ContextConfiguration(locations = {
		//"classpath:spring-test-datasource.xml",
		"classpath:spring-test-user-dao.xml"
		})
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {
	
	//@Autowired  
    private DataSource dataSource;
	
	//for dbunit
	protected IDatabaseConnection dbconn;
	
	//@Before
    public void before() throws Exception {
		dbconn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource),"CORE_SERVICE");
    }
	
	//@Test
	public void testConnection() {
		Assert.assertNotNull(dbconn);
	}
	
	@Test
	public void test() {
		Assert.assertTrue(true);
	}
	
}
