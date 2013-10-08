package com.np.trackserver.services.messaging;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	 final static Logger logger = Logger.getLogger(ConsumerService.class);
	
	 @Autowired
	 JmsTemplate jmsTemplate;
	 
	 @Autowired
	 MessageRegistry registry;
	 
	 /*
	 public void receiveMessage(Notification notification) {
		 //return (Notification) jmsTemplate.receiveAndConvert("test.sync.queue");
		 registry.registerNotification(notification);
	 }
	 */
	 
	 public void receiveMessage(Serializable message) {
		 //book keeping for later use
		 registry.registerMessage(message);
		 
		 try {
	         if (message instanceof TextMessage) {
	            TextMessage tm = (TextMessage) message;
	            String msg = tm.getText();
	         }
	      } catch (JMSException e) {
	         logger.error(e.getMessage(), e);
	      }
	 }
	 
	 
}
