package com.ssdi.Controller;

import com.ssdi.Entity.Product;
import com.ssdi.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by praya on 3/20/2017.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}