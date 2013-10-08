package com.np.trackserver.services.messaging;

import java.io.Serializable;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	 @Autowired
	 @Qualifier("jmsTemplate")
	 private JmsTemplate jmsTemplate;
	 
	 /**
	  * Default converter: SimpleMessageConverter
	  * This converter will be able to convert the following types:

    	String to TextMessage
    	Serializable to ObjectMessage
    	Map to MapMessage
    	byte[] to BytesMessage


	  * @param destination
	  * @param notification
	  */
	 public void handleNotification(String destination, Notification notification) {
		 jmsTemplate.convertAndSend(destination, notification); //uses SimpleMessageConverter
	 }
	 
	 public void handleMessage(Serializable message) {
	        jmsTemplate.convertAndSend(message); //uses SimpleMessageConverter    
	 } 
	 
}
