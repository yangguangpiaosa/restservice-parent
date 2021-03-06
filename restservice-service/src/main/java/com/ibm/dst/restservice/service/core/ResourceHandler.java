/**
 * 
 */
package com.ibm.dst.restservice.service.core;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.ibm.dst.restservice.service.core.filter.AuthorityFilter;
import com.ibm.dst.restservice.service.core.filter.DecodeFilter;

/**
 * @author 
 *
 */
@ApplicationPath("/api")
public class ResourceHandler extends ResourceConfig {
	
	private static final Logger logger = Logger.getLogger(ResourceHandler.class);
	
	public ResourceHandler() {
		logger.info("init service...");
		register(DecodeFilter.class);
		register(AuthorityFilter.class);
		StringBuffer packs = new StringBuffer("");
		packs.append("com.ibm.dst.restservice.service.system.resources");
		packs.append(",com.ibm.dst.restservice.service.user.resources");
		packages(packs.toString());
		//register(Test.class);
		
		register(MoxyXmlFeature.class);
		register(MoxyJsonFeature.class);
		//register(MOXyJsonProvider.class);
		//注册JSON转换器
		//register(JacksonJsonProvider.class);
		
		logger.info("done.");
	}
}
