package com.np.trackserver.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.np.trackserver.controllers.filter.AuthorizationFilter;

/**
 * Boot start class that loads application context and other servlets and beans. 
 * @author npatel
 *
 *https://spring.io/guides/gs/spring-boot/
 *http://docs.spring.io/spring-boot/docs/current/reference/html/auto-configuration-classes.html
 *http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html
 */
//@SpringBootApplication
@ComponentScan(basePackages = "com.np.trackserver")
@EnableWebMvc
@EnableAutoConfiguration(exclude={JmsAutoConfiguration.class})
@ImportResource(value={"classpath:trackserver-applicationContext.xml"})
public class Application {
	
	private static ApplicationContext appContext;
	
	public static void main(String[] args){
		
		initAppConfigLocation();
		appContext = SpringApplication.run(Application.class, args);
		System.out.println("Application Context is loaded now.. go nuts!");
		
	}

	private static void initAppConfigLocation() {

		String springProfile = System.getProperty("spring.profiles.active");
		if (StringUtils.isBlank(springProfile)) {
			System.setProperty("spring.profiles.active", "dev");
		}

	}
	/**
	 * Dispatcher servlet registration for handing over requests to resteasy framework
	 * @param dispatcherServlet
	 * @return
	 */
	/*@Bean
	public ServletRegistrationBean initServlet(DispatcherServlet dispatcherServlet) {

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(dispatcherServlet);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/*");
		servletRegistrationBean.setUrlMappings(urlList);
		return servletRegistrationBean;
	}*/
	
	/**
	 * Auth filter registration Do not register xbase filter when we run the
	 * app in noxbase mode
	 * 
	 * @return
	 */
	@Bean
	//@Profile("!dev")
	public FilterRegistrationBean initAuthFilter() {

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AuthorizationFilter());
		List<String> urlList = new ArrayList<String>();
		urlList.add("/users/*");
		urlList.add("/activities/*");
		filterRegistrationBean.setUrlPatterns(urlList);
		return filterRegistrationBean;
	}

	
	public static ApplicationContext getAppContext() {
		return appContext;
	}

}
