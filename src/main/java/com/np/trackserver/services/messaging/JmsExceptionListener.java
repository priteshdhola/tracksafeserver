package com.np.trackserver.services.messaging;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.stereotype.Service;

@Service
public class JmsExceptionListener implements ExceptionListener {

	@Override
	public void onException(JMSException e) {
		 e.printStackTrace();
	}

}
