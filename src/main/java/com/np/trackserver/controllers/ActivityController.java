package com.np.trackserver.controllers;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.springframework.stereotype.Controller;

import com.np.trackserver.services.ActivityService;
import com.np.trackserver.services.beans.ActivityData;

@Controller
@Path("/activities")
public class ActivityController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	ActivityService activityService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createActivity(@Context UriInfo uri, ActivityData activityData) {
		
		Throwable t = null;
		Object o = null;
		URI u = null;
		Integer id = null;
		
		try{
			id = activityService.createActivity(activityData);
			u = UriBuilder.fromUri(uri.getPath() + "/"+ id).build();
		
		} catch (Exception re) {
			re.printStackTrace();
			t = re;
		}
		Response response = buildResponse(o, u, t);
		return response;
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
	public Response getActivity(@PathParam("id") String id) {
		
		Throwable t = null;
		Object o = null;
		
		//TODO: check for valid userid and advertiserId should have been done before coming here 
		
		try {
			
			ActivityData activity = activityService.getActivity(Integer.valueOf(id));
			o = activity;
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
		
	}
	public void getActivities(Request req, Response res) {
		
	}
	public void updateActivity(Request req, Response res) {
		
	}
}
