/**
 * 
 */
package com.ibm.dst.restservice.service.core;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author 
 *
 */
@ApplicationPath("/api/v2")
public class ResourceHandlerV2 extends ResourceConfig {
	
	private static final Logger logger = Logger.getLogger(ResourceHandlerV2.class);
	
	public ResourceHandlerV2() {
		logger.info("init service...");
		packages("com.ibm.dst.restservice.service.v2.system.resources");
		//register(Version.class);
		logger.info("done.");
	}
}
