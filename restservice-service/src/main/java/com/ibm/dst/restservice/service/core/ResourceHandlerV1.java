/**
 * 
 */
package com.ibm.dst.restservice.service.core;

import org.apache.log4j.Logger;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author 
 *
 */
//@ApplicationPath("/api/v1")
public class ResourceHandlerV1 extends ResourceConfig {
	
	private static final Logger logger = Logger.getLogger(ResourceHandlerV1.class);
	
	public ResourceHandlerV1() {
		logger.info("init service...");
		packages("com.ibm.dst.restservice.service.v1.system.resources");
		//register(Test.class);
		
		register(MoxyXmlFeature.class);
		register(MoxyJsonFeature.class);
		//register(MOXyJsonProvider.class);
		//注册JSON转换器
		//register(JacksonJsonProvider.class);
		
		logger.info("done.");
	}
}
