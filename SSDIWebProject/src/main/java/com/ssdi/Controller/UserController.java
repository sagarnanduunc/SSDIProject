package com.ssdi.Controller;

import com.ssdi.Entity.*;
import com.ssdi.Service.*;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.util.UUID;

/**
 * Created by praya on 3/20/2017.
 */

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestBody User user) {
        System.out.println("user.getEmail()" + user.getEmail());
        String message = userService.addUser(user);
        return "{\"response\":\"" + message + "\"}";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String logout(@RequestBody User user, HttpSession httpSession) {
        httpSession.invalidate();
        return "{\"response\":\"Logout Successful\"}";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody User user, HttpSession httpSession) {
        System.out.println("user.getEmail()" + user.getEmail());
        System.out.println("user.getPassword()" + user.getPassword());
        boolean login = userService.checkLogin(user);
        if (login) {
            System.out.println("Login Successful");
            httpSession.setMaxInactiveInterval(20 * 60);
            httpSession.setAttribute("user", user);
        } else {
            return "{\"response\":\"Login Unsuccessful\"}";
        }
        System.out.println(httpSession.getAttribute("user"));
        return "{\"response\":\"Login Successful\"}";

    }

    @RequestMapping(value = "/addaddress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAddress(@RequestBody Address address, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        address.setEmail(user.getEmail());
        userService.addAddress(address);
    }

    @RequestMapping(value = "/addbankinfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBankInfo(@RequestBody Bank bank, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        bank.setEmail(user.getEmail());
        userService.addBankInfo(bank);
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public Collection<Address> getAllAddresses(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return userService.getAllAddresses(user.getEmail());
    }

    @RequestMapping(value = "/bankinfo", method = RequestMethod.GET)
    public Collection<Bank> getAllBankInfo(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return userService.getAllBankInfo(user.getEmail());
    }

    @RequestMapping(value = "/paymentinfo", method = RequestMethod.GET)
    public Collection<Payment> getPaymentInfo(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return userService.getPayment(user.getEmail());
    }

    @RequestMapping(value = "/addpaymentinfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPaymentinfo(@RequestBody Payment payment, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        payment.setEmail(user.getEmail());
        userService.addPaymentInfo(payment);
    }

    @RequestMapping(value = "/addtransactioninfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTransactioninfo(@RequestBody Collection <Transaction> transaction, HttpSession httpSession) throws ParseException{
        User user = (User) httpSession.getAttribute("user");
        for (Transaction t : transaction)
        {
            t.setEmail_renter(user.getEmail());
            userService.addTransactionInfo(t);
            System.out.println(t.getStart_date());
            productService.changeProductStatus(t.getProduct_id());
        }

    }
}
