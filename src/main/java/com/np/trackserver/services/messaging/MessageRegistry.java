package com.np.trackserver.services.messaging;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MessageRegistry {
	
	private static final Logger logger = Logger.getLogger(MessageRegistry.class);
	
	private BlockingQueue<Serializable> receivedMessages = new LinkedBlockingQueue<Serializable>();

	public BlockingQueue<Serializable> getReceivedMessages() {
		return receivedMessages;
	}

	public void registerMessage(Serializable message) {
		
		boolean registerd = receivedMessages.offer(message);
		if(!registerd){
			logger.error("MessageRegistry is full. Clean up old messages or increase registry size");
		}
	}
	
	public void clear() {
		receivedMessages.clear();
	}
	
	public Serializable poll(){
		return receivedMessages.poll();
	}

}
