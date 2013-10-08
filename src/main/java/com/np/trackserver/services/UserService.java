package com.np.trackserver.services;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.UserDAO;
import com.np.trackserver.dao.model.User;
import com.np.trackserver.exceptions.AuthenticationException;
import com.np.trackserver.exceptions.NoResourceFoundException;
import com.np.trackserver.services.beans.InviteMBean;
import com.np.trackserver.services.beans.LocationData;
import com.np.trackserver.services.beans.UserData;
import com.np.trackserver.services.messaging.ProducerService;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    
    @Autowired
    ProducerService producerService;

    private ConcurrentMap<Integer, ConcurrentMap<Integer, LocationData>> userActivityLocations = new ConcurrentHashMap<Integer, ConcurrentMap<Integer, LocationData>>();

    // Create User
    @Transactional()
    public Integer createUser(UserData user){

        Date cur = new Date();
        User dbUser = new User();

        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setCreatedDate(cur);
        dbUser.setModifiedDate(cur);
        dbUser = userDAO.save(dbUser);
        return dbUser.getId();
    }

    // Update User Information
    @Transactional()
    public void updateUser(UserData updatedUser){

        User dbUser = null;
        dbUser = userDAO.get(updatedUser.getId());

        if(null == dbUser){
            throw new NoResourceFoundException("No Such User Found");
        }
        // First get the db object and only set the values which are not already set.
        if(updatedUser.getAge() != null)
            dbUser.setAge(updatedUser.getAge());
        if(updatedUser.getHeight() != null)
            dbUser.setHeight(updatedUser.getHeight());
        if(updatedUser.getWeight() != null)
            dbUser.setWeight(updatedUser.getWeight());
        if(updatedUser.getSex() != null)
            dbUser.setSex(updatedUser.getSex());

        // Change the last modified entry
        Date cur = new Date();
        dbUser.setModifiedDate(cur);

        userDAO.update(dbUser);
    }

    @Transactional()
    public void authenticateUser(UserData user){

        User dbUser = null;
        dbUser = userDAO.get(user.getId());
        boolean result = false;

        if(null == dbUser){
            throw new NoResourceFoundException("No Such User Found");
        }

        // First get the db object and only set the values which are not already set.
        if((user.getEmail().equals(dbUser.getEmail())) && (user.getPassword().equals(dbUser.getPassword())))
            result = true;

        if(result == false)
            throw new AuthenticationException("Bad Credentials");
    }
    
    @Transactional()
    public void inviteUser(Integer curUserId, String inviteeEmail){
    	
    	//TODO: 
    	//check if user with given email exist
    	//if yes, send invitation to jms "invite to friend" queue
    	
    	//if not, send invitation to jms "invite to app" queue
        //userData.setId(id);
    	
    	InviteMBean i1 = new InviteMBean();
        i1.setId(curUserId);	
        i1.setInviteeEmail(inviteeEmail);
        i1.setInviteeId(2);
        i1.setInviteMessage("hi add me in your list");
        
        producerService.handleMessage(i1);
    }

}
