package com.np.trackserver.server;

import org.apache.log4j.Logger;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.np.trackserver.utils.SpringPropertyUtils;
import com.strategicgains.restexpress.RestExpress;

/**
 * Core Restexpress/netty based server class. It defines routes and maps exceptions
 * @author npatel
 *
 */
public class TrackServer implements InitializingBean {
	
	private static final Logger logger = Logger.getLogger(TrackServer.class);
	
	@Autowired
	RestExpress restServer;
	
	private static ApplicationContext appContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				TrackServer.this.terminate();
			}
		});
	}
	
	private void startServer() {
	
		restServer.setName("Track server");
		restServer.setDefaultFormat(SpringPropertyUtils.getProperty("track_server.default_format"));
		
		//Server settings
		restServer.setExecutorThreadCount(SpringPropertyUtils.getIntegerProperty("track_server.executorthread.count"));
		restServer.setIoThreadCount(SpringPropertyUtils.getIntegerProperty("track_server.workerthread.count"));
		
		restServer.setKeepAlive(SpringPropertyUtils.getBooleanProperty("track_server.keep_alive"));
		
		//Socket settings
		restServer.setConnectTimeoutMillis(SpringPropertyUtils.getIntegerProperty("track_server.sock.connect_time_out_millis"));
		restServer.setReceiveBufferSize(SpringPropertyUtils.getIntegerProperty("track_server.sock.received_buffer_size"));
		
		/*
		RestExpress restExpress = new RestExpress(new Routes(config))
	    .setName(config.getServerName())
	    .setPort(config.getServerPort())
	    .setDefaultFormat(config.getDefaultFormat())
	    .setWorkerThreadCount(config.getWorkerThreadCount())
	    .setExecutorThreadCount(config.getExecutorThreadCount())
	    .setUseKeepAlive(true)
	    .supportTxt()
	    .putResponseProcessor(Format.JSON, ResponseProcessors.json())
		.putResponseProcessor(Format.XML, ResponseProcessors.xml())
	    .putResponseProcessor(Format.WRAPPED_JSON, ResponseProcessors.wrappedJson())
	    .putResponseProcessor(Format.WRAPPED_XML, ResponseProcessors.wrappedXml())
	    .addMessageObserver(new ExceptionMessageObserver())
	    .addMessageObserver(new TimerMessageObserver());
		
		
		new RoutesMetadataPlugin()							// Support basic discoverability.
		.register(server)
		.parameter(Parameters.Cache.MAX_AGE, 86400);	// Cache for 1 day (24 hours).

		new CacheControlPlugin()							// Support caching headers.
			.register(server);
	
		mapExceptions(server);
		registerDomainEvents(server, config);
		
		*/
		defineRoutes();
		
		restServer.bind(8081);
		restServer.awaitShutdown();
		
	}
	
	private void terminate() {

		logger.info("Stopping Track Server ...");
		restServer.shutdown();
		restServer = null;
		logger.info("Track server stopped!!");
	}
	
	public static void main(String[] args) throws Exception
	{
		
		try {

			appContext = new ClassPathXmlApplicationContext("trackserver-applicationContext.xml");
			
			TrackServer trackServer = appContext.getBean(TrackServer.class);
			
			//Start REST API
			trackServer.startServer();
			
			logger.info("Track-server is started!!");
			
		} catch (Exception e) {
			logger.error("Track-server Start Up failed.", e);
			System.exit(1);
		}
		
	    
	}

	
    private void defineRoutes() {
	
    	restServer.uri("/v1/events.{format}", null)
				.action("createEvent", HttpMethod.POST);
    	
    	restServer.uri("/v1/events.{format}", null)
				.action("getEvents", HttpMethod.GET);
    	
    	restServer.uri("/v1/events/{eventId}.{format}", null)
				.action("updateEvent", HttpMethod.PUT);
		
    	restServer.uri("/v1/events/{eventId}.{format}", null)
				.action("getEvent", HttpMethod.GET);
    	
	
    }

	/**
     * @param server
     */
    private static void mapExceptions(RestExpress server) {
//    	server
//    	.mapException(ItemNotFoundException.class, NotFoundException.class)
//    	.mapException(DuplicateItemException.class, ConflictException.class)
//    	.mapException(ValidationException.class, BadRequestException.class);
    }

	
}
