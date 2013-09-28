package com.np.trackserver.controllers;

import com.np.trackserver.services.UserService;
import com.np.trackserver.services.beans.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Controller
@Path("/users")
public class UserController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;
    private final int temp_user_id = 2;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(@Context UriInfo uri, @Context Request req, @Context Response res, UserData userData) {

        Throwable t = null;
        Object o = null;
        URI u = null;
        Integer id = null;

        try{
            id = userService.createUser(userData);
            u = UriBuilder.fromUri(uri.getPath() + "/"+ id).build();

        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, u, t);
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
    @Path("/auth")
    public Response authenticateUser(@Context UriInfo uri, @Context Request req, @Context Response res, UserData userData) {

        Throwable t = null;
        Object o = null;
        URI u = null;
        Integer id = temp_user_id;

        try{
            userData.setId(id);
            userService.authenticateUser(userData);
            u = UriBuilder.fromUri(uri.getPath() + "/"+ id).build();

        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, u, t);
        return response;
    }
}
