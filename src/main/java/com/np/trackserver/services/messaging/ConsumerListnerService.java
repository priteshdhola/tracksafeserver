package com.np.trackserver.services.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

//@Service
public class ConsumerListnerService implements MessageListener {

	final static Logger logger = Logger.getLogger(ConsumerListnerService.class);

	private int numOfMessages = 0;
	   
	@Override
	public void onMessage(Message message) {
		try {
	         numOfMessages++;
	         if (message instanceof TextMessage) {
	            TextMessage tm = (TextMessage) message;
	            String msg = tm.getText();
	            logger.info(">>>>>Processed message: " + msg + " - numOfMessages : " + numOfMessages);
	         }
	      } catch (JMSException e) {
	         logger.error(e.getMessage(), e);
	      }
		
	}
	
	
}
