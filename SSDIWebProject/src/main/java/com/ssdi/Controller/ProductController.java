package com.ssdi.Controller;

import com.ssdi.Entity.*;
import com.ssdi.Service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getavailableproducts", method = RequestMethod.GET)
    public Collection<Product> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/filterbycategory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getProductsByCategory(@RequestBody Collection<Category> category) {
        return productService.getProductsByCategory(category);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> searchProduct(@RequestBody String s) throws org.json.JSONException {
        JSONObject obj = new JSONObject(s);
        String searchString = obj.getString("searchString");
        return productService.searchProducts(searchString);
    }

    @RequestMapping(value = "/filterbyprice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getProductsByPrice(@RequestBody PriceRange priceRange) {
        return productService.getProductsByPrice(priceRange);
    }

    @RequestMapping(value = "/getallcategories", method = RequestMethod.GET)
    public ArrayList<String> getAllCategories() {
        return productService.getAllCategories();
    }

//    @RequestMapping(value = "/addproduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String addProduct(@RequestBody Product product, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        product.setEmail(user.getEmail());
//        System.out.println("product.getEmail()" + product.getEmail());
//        String message = productService.addProduct(product);
//        return "{\"response\":\"" + message + "\"}";
//    }


    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    public String saveUserDataAndFile(@RequestParam(value = "description") String description, @RequestParam(value = "price") float price, @RequestParam(value = "name") String name, @RequestParam(value = "category") String category, @RequestParam(value = "file") MultipartFile file, HttpSession request) {
        User user = (User) request.getAttribute("user");
        Product product = new Product();
        //System.out.println("product.getEmail()" + user.getEmail());
        product.setEmail(user.getEmail());
        System.out.println("product.getEmail()" + product.getEmail());
        //System.out.println(name);
        name=name.replace("\"","");
        description=description.replace("\"","");
        category=category.replace("\"","");
        product.setName(name);
        System.out.println(product.getName());
        product.setCategory(category);
        System.out.println(product.getCategory());
        product.setDescription(description);
        System.out.println(product.getDescription());
        product.setPrice(price);
        //System.out.println(product.getPrice());
        //System.out.println("product.getEmail()" + product.getEmail());
        //System.out.println("Inside File upload");
        //String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String rootDirectory = "C:\\Users\\Admin\\Desktop\\Study\\SSDI\\ProjectWork1\\SSDIProject\\SSDIWebProject\\src\\main\\resources\\static\\img\\";
        System.out.println("Root Directory " + rootDirectory);
        String directory = "img/logan.jpg";
        try {
            file.transferTo(new File(rootDirectory + file.getOriginalFilename()));
            System.out.println(rootDirectory + file.getOriginalFilename());
            directory = "img/" + file.getOriginalFilename();
            System.out.println("Hey check");
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setPhotoLink(directory);
        //System.out.println(product.getPhotoLink());

        String message = productService.addProduct(product);
        return "{\"response\":\"" + message + "\"}";
    }

    @RequestMapping(value = "/addreview", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String leaveAReview(@RequestBody Review review, HttpSession httpSession) {
        System.out.println("review.getEmail: " + review.getReviewer());
        System.out.println("review.getProductId: " + review.getProductId());
        User user = (User) httpSession.getAttribute("user");
        review.setReviewer(user.getEmail());
        String message = productService.addReview(review);
        return "{\"response\":\"" + message + "\"}";
    }

    @RequestMapping(value = "/getreviews", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Review> getReviews(@RequestBody int id) {
        return productService.getReviews(id);
    }

    @RequestMapping(value = "/myrating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int getRatingByUser(@RequestBody Product product, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return productService.getRatingByUser(product, user);
    }

    @RequestMapping(value = "/averagerating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public double getAverageRating(@RequestBody Product product) {
        return productService.getAverageRating(product);
    }

    @RequestMapping(value = "/addrating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRating(@RequestBody Product product, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        String message = productService.addRating(product, user, product.getRating());
        return "{\"response\":\"" + message + "\"}";
    }
}