package com.np.trackserver.services;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.UserActivityDAO;
import com.np.trackserver.dao.UserDAO;
import com.np.trackserver.dao.model.Activity;
import com.np.trackserver.dao.model.User;
import com.np.trackserver.dao.model.UserActivity;
import com.np.trackserver.services.beans.ActivityData;
import com.np.trackserver.services.beans.LocationData;
import com.np.trackserver.services.beans.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    private ConcurrentMap<Integer, ConcurrentMap<Integer, LocationData>> userActivityLocations = new ConcurrentHashMap<Integer, ConcurrentMap<Integer, LocationData>>();

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

    @Transactional()
    public void updateUser(UserData updatedUser){

        User dbUser = null;
        dbUser = userDAO.get(updatedUser.getId());

        if(null == dbUser){
            throw new RuntimeException("no user found ");
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

}
