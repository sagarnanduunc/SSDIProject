package com.ssdi.Controller;

import com.ssdi.Entity.User;
import com.ssdi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;

/**
 * Created by praya on 3/20/2017.
 */

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
