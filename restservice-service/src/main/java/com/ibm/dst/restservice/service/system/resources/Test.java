package com.ibm.dst.restservice.service.system.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class Test {
	@GET
    @Produces("text/plain")
    public String getIt() {
        return "Test.Got it!...";
    }
}
