package com.np.trackserver.controllers;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.np.trackserver.services.UserService;
import com.np.trackserver.services.beans.UserData;

@Controller
@Path("/users")
public class UserController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;
    private final int temp_user_id = 2;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
	public Response getUser(@Context Request req, @Context Response res, @PathParam("id") Integer id) {
		
		Throwable t = null;
		Object o = null;
		try {
			
			UserData user = userService.getUser(id);
			o = user;
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
	}
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(@Context UriInfo uri, @Context Request req, @Context Response res, UserData userData) {

        Throwable t = null;
        Object o = null;
        URI u = null;
        Integer id = temp_user_id;

        try{
            userData.setId(id);
            userService.updateUser(userData);
            u = UriBuilder.fromUri(uri.getPath() + "/"+ id).build();

        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, u, t);
        return response;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/invite/{email}")
    public Response inviteUser(@Context Request req, @Context Response res, @PathParam("email") String email) {

        Throwable t = null;
        Object o = null;
        URI u = null;
        Integer id = temp_user_id;

        try{
        	
            userService.inviteUser(id, email);

        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, u, t);
        return response;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/invite/accept/{host_id}")
    public Response acceptInvitation(@Context Request req, @Context Response res, 
    																@PathParam("host_id") Integer hostId) {

        Throwable t = null;
        Object o = null;
        URI u = null;
        Integer id = 2;

        try{
        	
            userService.acceptInvitation(id, hostId);

        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, u, t);
        return response;
    }
}
