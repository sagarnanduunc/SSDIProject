package com.ssdi.Controller;

import com.ssdi.Entity.Category;
import com.ssdi.Entity.Product;
import com.ssdi.Service.ProductService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import org.json.*;

/**
 * Created by prayas on 3/20/2017.
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

    @RequestMapping(value = "/filterbycategory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getProductsyCategory(@RequestBody Collection<Category> category) {
        return productService.getProductsByCategory(category);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> searchProduct(@RequestBody String s) throws org.json.JSONException{
        JSONObject obj = new JSONObject(s);
        String searchString = obj.getString("searchString");
        return productService.searchProducts(searchString);
    }

}