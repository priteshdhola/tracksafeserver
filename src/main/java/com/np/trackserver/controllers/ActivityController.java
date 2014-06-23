package com.np.trackserver.controllers;

import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.np.trackserver.services.ActivityService;
import com.np.trackserver.services.beans.ActivityData;
import com.np.trackserver.services.beans.ActivityDataList;
import com.np.trackserver.services.beans.LocationData;
import com.np.trackserver.services.beans.UserActivityData;
import com.np.trackserver.services.beans.UserActivityLocationData;
import com.np.trackserver.services.beans.UserActivityLocationDataList;
import com.np.trackserver.services.beans.UserData;

@Controller
@Path("/activities")
public class ActivityController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	ActivityService activityService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createActivity(@Context UriInfo uri, @Context HttpServletRequest req, @Context Response res, ActivityData activityData) {
		
		Throwable t = null;
		Object o = null;
		URI u = null;
		Integer id = null;
		
		UserData user = (UserData)req.getSession().getAttribute("userdata");
		
		activityData.setCreatedBy(user.getId());
		
		try{
			id = activityService.createActivity(activityData, user);
			u = UriBuilder.fromUri(uri.getPath() + "/"+ id).build();
		
		} catch (Exception re) {
			re.printStackTrace();
			t = re;
		}
		Response response = buildResponse(o, u, t);
		return response;
		
	}
	
	@POST
	@Path("/{activity_id}/updateStatus")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateActivityStatus(@Context UriInfo uri, @Context HttpServletRequest req, @Context Response res, @PathParam("activity_id") Integer activityId,
			UserActivityData userAactivityData) {
		
		Throwable t = null;
		Object o = null;
		URI u = null;
		
		UserData user = (UserData)req.getSession().getAttribute("userdata");
		
		try{
			activityService.updateActivityStatus(userAactivityData, activityId, user.getId());
		
		} catch (Exception re) {
			re.printStackTrace();
			t = re;
		}
		Response response = buildResponse(o, u, t);
		return response;
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{activity_id}")
	public Response getActivity(@Context HttpServletRequest req, @Context Response res,
			@PathParam("activity_id") Integer id) {
		
		Throwable t = null;
		Object o = null;
		try {
			
			UserData user =  (UserData)req.getSession().getAttribute("userdata");
			
			ActivityData activity = activityService.getActivity(id, user.getId());
			o = activity;
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getActivities(@Context HttpServletRequest req, @Context Response res) {
		
		Throwable t = null;
		Object o = null;
		try {
			UserData user =  (UserData)req.getSession().getAttribute("userdata");
			List<ActivityData> activityList = activityService.getActivitiesByUserId(user.getId());
			ActivityDataList page = new ActivityDataList();
			page.setActivityList(activityList);
			o = page;
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
		
		
	}
	public void updateActivity(HttpServletRequest req, Response res) {
		
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{activity_id}")
	public Response trackUserLocationForActivity(@Context HttpServletRequest req, @Context Response res, @PathParam("activity_id") Integer activityId,
			UserActivityLocationData userActivityLocation) {
		
		Throwable t = null;
		Object o = null;
		try {
			UserData user =  (UserData)req.getSession().getAttribute("userdata");
			UserActivityData userActivityData = userActivityLocation.getUserActivityData();
			userActivityData.setUser(user);
			activityService.trackUserLocationForActivity(activityId, userActivityLocation);
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
		
	}
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{activity_id}/userLocations")
	public Response getUserLocationForActivity(@Context HttpServletRequest req, @Context Response res,
			@PathParam("activity_id") Integer activityId) {
		
		Throwable t = null;
		Object o = null;
		try {
		
			List<UserActivityLocationData> usersLocation = activityService.getUsersLocationForActivity(activityId);
			
			UserActivityLocationDataList page = new UserActivityLocationDataList();
			page.setUserActivityLocationDataList(usersLocation);
			o = page;
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
		
	}
	
	/**
	 * THis method is test method to mokc locations of various mock users
	 * @param req
	 * @param res
	 * @param activityId
	 * @param userId
	 * @param location
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{activity_id}/{user_id}")
	public Response trackUserLocationForActivity1(@Context HttpServletRequest req, @Context Response res, @PathParam("activity_id") Integer activityId, @PathParam("user_id") Integer userId,
			UserActivityLocationData location) {
		
		Throwable t = null;
		Object o = null;
		try {
			UserData user = new UserData();
			user.setUserName("test user :"+new Random().nextInt());
			user.setId(userId);
			location.getUserActivityData().setUser(user);
			activityService.trackUserLocationForActivity(activityId, location);
			
		} catch (Exception re) {
			t = re;
		}
		Response response = buildResponse(o, t);
		return response;
		
	}
	
}
