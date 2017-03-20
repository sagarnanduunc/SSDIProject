package com.ssdi.Service;

import com.ssdi.Dao.IUserDao;
import com.ssdi.Dao.UserDao;
import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

@Service
public class UserService {
    @Autowired
    @Qualifier("user")
    private UserDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByEmail(String email){
        return this.userDao.getUserByEmail(email);
    }

}
