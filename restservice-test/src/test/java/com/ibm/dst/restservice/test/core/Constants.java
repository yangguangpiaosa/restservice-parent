/**
 * 
 */
package com.ibm.dst.restservice.test.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author 
 *
 */
public class Constants {
	
	private static final Logger logger = Logger.getLogger(Constants.class);
	
	public static Map<String, String> propertyMap = null;
	
	static {
		try {
			propertyMap = new HashMap<String, String> ();
			Properties properties = new Properties();
			File propertyFile = new File(Constants.class.getResource("/").getPath() + "resources.properties");
			properties.load(new FileInputStream(propertyFile));
			Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				propertyMap.put(entry.getKey().toString(), entry.getValue().toString());
			}
		} catch (FileNotFoundException e) {
			logger.error("Error loading properties:", e);
		} catch (IOException e) {
			logger.error("Error loading properties:", e);
		}
	}
	
}
