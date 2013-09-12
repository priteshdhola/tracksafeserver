package com.np.trackserver.controllers;

import java.net.URI;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.np.trackserver.services.beans.ResponseStatus;

public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	public ApplicationContext appContext;

	public BaseController() {
	}

	public ApplicationContext getContext() {
		return appContext;
	}

	protected Response buildResponse(Object o, Throwable e) throws WebApplicationException {
		return buildResponse(o, null, e);
	}

	protected Response buildResponse(Throwable e) throws WebApplicationException {
		return buildResponse(null, null, e);
	}

	protected Response buildResponse(Object o, URI contentLocation) throws WebApplicationException {
		return buildResponse(o, contentLocation, null);
	}

	protected Response buildResponse(Object o, URI contentLocation, Throwable e) throws WebApplicationException {
		
		boolean bErrored = false;
		Response.ResponseBuilder builder = null;
		
		if (null != e) {
			logger.warn("SERVICE Exception: " + e.getMessage());
			return buildExceptionResponse(e, o);
		}

		if (!bErrored) {
			logger.debug("Building normal REST response.");
			builder = Response.ok(o);
			if (null != contentLocation) {
				builder.contentLocation(contentLocation);
			}
		}

		return builder.build();
	}

	private Response buildExceptionResponse(Throwable e, Object o) {
		
		Response.Status httpStatus;
		String quovaCode;
		String description;
		quovaCode = e.getClass().getName(); // TODO: this may be TMI for the caller
		description = e.getMessage(); // the throwing module should be as specific as it can be. in case of 500 it may
										// be TMI

		if (e instanceof IllegalArgumentException) {
			httpStatus = Response.Status.BAD_REQUEST;
			
		} else if (e instanceof IllegalStateException) {
			httpStatus = Response.Status.NOT_ACCEPTABLE;
			
		} else {
			// includes DataStoreException and all other unmapped exceptions
			logger.error("Platform service server failure: ", e);
			httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
			
		}

		return buildResponse(httpStatus, quovaCode, description);
	}
	
	/**
	 * Build a generic response given the response code.
	 * 
	 * @param httpStatus
	 *            Required.
	 * @param description
	 *            Optional.
	 * @param quovaCode
	 *            Optional.
	 * @return The Response object.
	 */
	protected Response buildResponse(Response.Status httpStatus, String quovaCode, String description) {
		
		ResponseStatus status = new ResponseStatus();
		status.setStatusCode(httpStatus.getStatusCode());
		status.setSubCode(quovaCode);
		status.setDescription(description);

		Response.ResponseBuilder builder = Response.status(httpStatus);
		builder.entity(status);
		return builder.build();
	}


}
