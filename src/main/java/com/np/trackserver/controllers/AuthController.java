package com.np.trackserver.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.np.trackserver.services.UserService;
import com.np.trackserver.services.beans.UserData;


@Controller
@Path("/auth")
public class AuthController extends BaseController {
    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    UserService userService;

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
            String uriStr = uri.getPath();
            uriStr = uriStr.substring(0, uriStr.lastIndexOf("/auth"));
            uriStr = uriStr + "/users";
            u = UriBuilder.fromUri(uriStr + "/"+ id).build();

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
    public Response authenticateUser(@Context UriInfo uri, @Context Request req, @Context Response res, @Context HttpSession session,
    		@Context HttpServletRequest request, UserData userData, @QueryParam("tk") @DefaultValue("") String token) {

        Throwable t = null;
        Object o = null;
        URI u = null;

        try{
            
        	if(StringUtils.isEmpty(token)){
        		userData = userService.authenticateUser(userData);
        		
        	} else {
        		
        	}
            
            //create session
			session = request.getSession (true);
			if(session.isNew() == false){
				session.invalidate();
				session = request.getSession (true);
			}
			
			session.setAttribute("user", userData);
			
        } catch (Exception re) {
            re.printStackTrace();
            t = re;
        }
        Response response = buildResponse(o, t);
        return response;
    }
    
   
}
