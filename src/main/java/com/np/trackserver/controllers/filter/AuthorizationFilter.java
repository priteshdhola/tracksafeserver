package com.np.trackserver.controllers.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.np.trackserver.services.beans.UserData;

@Controller
public class AuthorizationFilter implements Filter {
	

	private static final Logger logger = Logger.getLogger(AuthorizationFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean authorized = false;
		UserData user = null;
		
		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if (session != null) {
				
				user = (UserData)session.getAttribute("userdata");
				authorized = user != null && user.getId() != null;
			}
		}
		//this is to bypass auth; remove this in prod
		if(!authorized){
			
			if(user == null){
				user = new UserData();
				user.setId(2);
				((HttpServletRequest) request).getSession(true).setAttribute("userdata", user);
			}
			authorized = true;
			
		}
		//...
		
		if (authorized) {
			chain.doFilter(request, response);
		} else if (response instanceof HttpServletResponse) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			throw new ServletException("A valid session is required!!");
		}
		
	}

	public void destroy() {}

}
