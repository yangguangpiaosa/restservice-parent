package com.ibm.dst.restservice.service.system.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ibm.dst.restservice.service.core.Resource;
import com.ibm.dst.restservice.service.system.model.VersionModel;

//@Path("/version")
public class Version extends Resource {
	
	private static final Logger logger = Logger.getLogger(Version.class);
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{x}")
    public Response getIt(@PathParam("x") String ext, @QueryParam("v") String ver, @Context Request request) {
		logger.info("version resource.");
		VersionModel version = new VersionModel();
		version.setVersionNumber(ext + ver);
		version.setETag(new EntityTag(String.valueOf(version.getVersionNumber().hashCode())));
		return ifNotModified(version, request);
    }
	
}
