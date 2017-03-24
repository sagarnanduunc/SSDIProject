package com.ssdi.Controller;

import com.ssdi.Entity.User;
import com.ssdi.Service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    @RequestMapping(value = "/adduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        System.out.println("user.getEmail()" + user.getEmail());
        userService.addUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody User user, HttpSession httpSession){
        System.out.println("user.getEmail()" + user.getEmail());
        System.out.println("user.getPassword()" + user.getPassword());
        boolean login = userService.checkLogin(user);
        if (login){
            System.out.println("Login Successful");
            httpSession.setAttribute("user", user);
        }
        else {
            return "{\"response\":\"Login Unsuccessful\"}";
        }
        System.out.println(httpSession.getAttribute("user"));
        return "{\"response\":\"Login Unsuccessful\"}";

        /*
        if (login){
            System.out.println("Login Successful");
            //Cookie loginCookie = new Cookie("user", user.getEmail());
            UUID uid = (UUID) httpSession.getAttribute("user");
            if (uid == null) {
                uid = UUID.randomUUID();
            }
            httpSession.setAttribute("user", uid);
            System.out.println(uid.toString());
        }
        */

    }

}
