package com.np.tracksserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.np.trackserver.services.beans.InviteMBean;
import com.np.trackserver.services.messaging.ConsumerService;
import com.np.trackserver.services.messaging.MessageRegistry;
import com.np.trackserver.services.messaging.ProducerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:trackserver-applicationContext.xml"})
public class MessagingServiceTest {
	
	@Autowired
	ProducerService producerService;
	
	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	MessageRegistry registry;
	
	@Test
    public void testSendConcurentMessages() {      
		
        List<InviteMBean> messages = new ArrayList<InviteMBean>();
        
        InviteMBean i1 = new InviteMBean();
        i1.setId(1);	
        i1.setInviteeEmail("abc@gmail.com");
        i1.setInviteeId(2);
        i1.setInviteMessage("hi add me in your list");
        
        InviteMBean i2 = new InviteMBean();
        i2.setId(1);
        i2.setInviteeEmail("xyz@gmail.com");
        i2.setInviteeId(3);
        i2.setInviteMessage("hi add me in your list");
        
        messages.add(i1);
        messages.add(i2);
       
        for (InviteMBean invite : messages) {
        	
            new Thread(new Producer(producerService, invite)).start();
        }
         
        //needed so that the test does not end before the listener
        //could display the messages to the console
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        Assert.assertEquals(messages.size(), registry.getReceivedMessages().size());
    }
	 
	private static class Producer implements Runnable {
         
	        private Serializable message;
	        private ProducerService producer;
	         
	        public Producer(ProducerService sender, Serializable message) {
	            this.producer = sender;
	            this.message = message;
	        }
	                 
	        public void run() {
	            producer.handleMessage(message);
	        }      
	 }

}
