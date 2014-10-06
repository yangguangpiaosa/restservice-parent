/**
 * 
 */
package com.ibm.dst.restservice.service.core.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ibm.dst.restservice.service.core.Message;

/**
 * @author 
 *
 */
public class AuthorityFilter implements ContainerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(AuthorityFilter.class);

	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String appId = requestContext.getHeaders().get("AppID")==null?"None":(String)requestContext.getHeaders().get("AppID").get(0);
		String appKey = requestContext.getHeaders().get("AppKey")==null?"None":(String)requestContext.getHeaders().get("AppKey").get(0);
		if (!checkAuthority(appId, appKey)) {
			logger.error("Unauthorized request,AppID:"+appId+",AppKey:***************");
			Message unauth = new Message(40100, "Unauthorized access!");
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(unauth).build());
		} else {
			logger.info("Authorized request,AppID:"+appId+",AppKey:***************");
		}
	}
	
	//Check authority
	private boolean checkAuthority(String appId, String appKey) {
		if(appId.equals("id") && appKey.equals("key"))
			return true;
		else
			return false;
	}

}
