package com.ibm.dst.restservice.service.v1.system.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ibm.dst.restservice.service.core.Resource;
import com.ibm.dst.restservice.service.v1.system.model.AboutModel;
import com.ibm.dst.restservice.service.v1.system.model.VersionModel;

@Path("")
public class System extends Resource {
	
	private static final Logger logger = Logger.getLogger(System.class);
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/version")
    public Response getVersion(@Context Request request) {
		logger.info("Get version information.");
		VersionModel version = new VersionModel();
		version.setVersionNumber("1.0");
		version.setETag(new EntityTag(String.valueOf(version.getVersionNumber().hashCode())));
		return ifNotModified(version, request);
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/about")
	public Response getAbout(@Context Request request) {
		logger.info("Get about information.");
		AboutModel about = new AboutModel();
		about.setAuthor("Bob");
		about.setReleaseDate("2014-09-17");
		about.setDescription("Description");
		about.setContactInfo("not now.");
		about.setETag(new EntityTag(String.valueOf((about.getAuthor()+about.getReleaseDate()).hashCode())));
		return ifNotModified(about, request);
	}
	
}
