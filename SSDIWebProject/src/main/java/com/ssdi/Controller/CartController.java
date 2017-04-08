package com.ssdi.Controller;

import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import com.ssdi.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by Jacob on 4/4/2017.
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@RequestBody Product product, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        String message = cartService.addProduct(product, user);
        return "{\"response\":\"" + message + "\"}";
    }

    @RequestMapping(value = "/getcart", method = RequestMethod.GET)
    public Collection<Product> getProductsInCart(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return cartService.getProductsInCart(user);
    }

    @RequestMapping(value = "/checkoutcart", method = RequestMethod.GET)
    public Collection<Product> checkoutCart(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return cartService.checkoutCart(user);
    }
}
