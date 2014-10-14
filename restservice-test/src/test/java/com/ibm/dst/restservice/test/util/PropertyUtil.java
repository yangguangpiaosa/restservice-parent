/**
 * 
 */
package com.ibm.dst.restservice.test.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author 
 *
 */
public class PropertyUtil {
	
	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
	
	private static final ResourceBundle PERSONAL_BUNDLE = ResourceBundle.getBundle("personal");
	
	private static final ResourceBundle INIT_BUNDLE = ResourceBundle.getBundle("init");
	
	/**
	 * Load initial parameters
	 * @param key
	 * @return String
	 */
	public static String getProperty(String key) {
		String propertyValue = "";
		
		//Load personal configuration first
		if (PERSONAL_BUNDLE.containsKey(key)) {
			propertyValue = PERSONAL_BUNDLE.getString(key);
			if(null != propertyValue && !"".equals(propertyValue)) {
				logger.info("Load from personal configuration: " + key);
				return propertyValue;
			}
		}
		//Load default configuration
		if (INIT_BUNDLE.containsKey(key)) {
			propertyValue = INIT_BUNDLE.getString(key);
			if(null != propertyValue && !"".equals(propertyValue)) {
				//logger.info("Load from default configuration: " + key);
				return propertyValue;
			}
		}
		
		return propertyValue;
	}
	
}
