package com.ibm.dst.restservice.service.v2.system.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/version")
public class Version {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        return "{'version':'2.0'}";
    }
}
