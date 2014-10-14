/**
 * 
 */
package com.ibm.dst.restservice.test.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author 
 *
 */
public class PropertyUtilTest {
	
	@Test
	public void testGetProperty() {
		String value = PropertyUtil.getProperty("db.driver");
		Assert.assertEquals("com.ibm.db2.jcc.DB2Driver", value);
	}
	
}
