/**
 * 
 */
package com.ibm.dst.restservice.service.user.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.dst.restservice.service.core.BaseResource;
import com.ibm.dst.restservice.service.user.model.UserModel;
import com.ibm.dst.restservice.service.user.model.UsersModel;
import com.ibm.dst.restservice.service.user.service.UserService;

/**
 * @author 
 *
 */
@Path("/users")
public class User extends BaseResource {
	
	private static final Logger logger = Logger.getLogger(User.class);
	
	@Autowired
	private UserService userService = null;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@Context Request request) {
		logger.info("Get all users.");
		UsersModel users = userService.getUsers();
		return Response.ok(users).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getUserById(@PathParam("id") String id, @Context Request request) {
		logger.info("Get user by id.");
		if(id.equals("1")) {
			UserModel user = new UserModel();
			user.setId(1);
			user.setName("test1");
			user.setEmail("test1@cn.ibm.com");
			user.setETag(new EntityTag(String.valueOf(user.getId().hashCode())));
			return ifNotModified(user, request);
		} else {
			return ifNotModified(null, request);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/update")
	public Response updUserById(@PathParam("id") String id, UserModel user, @Context Request request) {
		logger.info("Update user by id - " + id);
		if(id.equals("1")) {
			if(null != user.getName() && !"".equals(user.getName())) {
				logger.info(user.getName());
				return message(Response.Status.OK, 20000, null);
			}
			else
				return message(Response.Status.BAD_REQUEST, 40000, new Exception("Bad request."));
		} else {
			return message(Response.Status.BAD_REQUEST, 40000, new Exception("Bad request."));
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response delUserById(@PathParam("id") String id, @Context Request request) {
		logger.info("Delete user by id - " + id);
		if(id.equals("1"))
			return message(Response.Status.OK, 20000, null);
		else
			return message(Response.Status.BAD_REQUEST, 40000, new Exception("Bad request."));
	}
	
}
