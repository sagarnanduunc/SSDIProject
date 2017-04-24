package com.test;

import com.ssdi.Controller.ProductController;
import com.ssdi.Controller.UserController;
import com.ssdi.Entity.*;
import com.ssdi.Service.ProductService;
import com.ssdi.Service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prayas on 3/26/2017.
 */

//@Transactional
public class ProductControllerMocksTest extends AbstractControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Collection<Product> getProductStubData() {
        Collection<Product> products = new ArrayList<Product>();
        products.add(getEntityStubData());
        return products;
    }

    private Product getEntityStubData() {
        File f = new File("C:\\photo\\a.jpeg");
        Product product = new Product();
        product.setName("table");
        product.setDescription("good quality table");
        product.setPrice(5);
        product.setEmail("vijay@uncc.edu");
        return product;
    }

    private Collection<Category> getCategoryStubData() {
        Collection<Category> category = new ArrayList<Category>();
        Category category1 = new Category("Furniture",1);
        Category category2 = new Category("Movies",1);
        category.add(category1);
        category.add(category2);
        return category;
    }

    private ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("furniture");
        categories.add("movies");
        categories.add("games");
        return categories;
    }

    private Collection<PriceRange> getPriceRangeStubData() {
        Collection<PriceRange> priceRanges = new ArrayList<PriceRange>();
        PriceRange PriceRange = new PriceRange(10,20);
        priceRanges.add(PriceRange);
        return priceRanges;
    }

    private Collection<Review> getReviewStubData() {
        Collection<Review> reviews = new ArrayList<Review>();
        Review review = new Review("Vijay", 1, "Very good Product");
        reviews.add(review);
        return reviews;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(productController);
    }

    @Test //
    public void testGetAllProducts() throws Exception {
        Collection<Product> products = getProductStubData();
        when(productService.getAllProducts()).thenReturn(products);
        String uri = "/products";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(productService, times(1)).getAllProducts();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testAddProductNegative() throws Exception {

        String uri = "/products/addproduct";
        Product product = new Product();
        String inputJson = super.mapToJson(product);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() < 1);


    }

    @Test
    public void testAddProduct() throws Exception {
        ArrayList<String> a = new ArrayList<>();
        a.add("Photo1");
        File f = new File("D:/abc/cd.png");
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        //Product product = new Product("Chair","4 legs",5,"furniture",a,"vijay@uncc.edu",f);
        String uri = "/products/addproduct";
        Product product = new Product();
//        product.setName("table");
//        product.setDescription("good table");
//        product.setEmail("vijay@uncc.edu");
//        product.setCategory("Game");
//        product.setPrice(5);
        String inputJson = super.mapToJson(product);

        when(productService.addProduct(product)).thenReturn("{\"response\":\"Product added successfully\"}");
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                        .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

    @Test
    public void testGetProductsByCategoty() throws Exception {

        String uri = "/products/filterbycategory";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(getCategoryStubData());

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testSearchProduct() throws Exception {

        String uri = "/products/search";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(getProductStubData());
        when(productService.searchProducts("chair")).thenReturn(getProductStubData());
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testGetProductsByPrice() throws Exception {

        String uri = "/products/filterbyprice";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        PriceRange PriceRange = new PriceRange(10,20);
        String inputJson = super.mapToJson(PriceRange);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        when(productService.getAllCategories()).thenReturn(getCategories());
        String uri = "products/getallcategories";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        //verify(productService, times(1)).getAllCategories();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testLeaveReview() throws Exception {

        String uri = "/products/addreview";
        Review review = new Review("Vijay", 1, "Very good Product");
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        when(productService.addReview(review)).thenReturn("Review added successfully");
        String inputJson = super.mapToJson(review);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user",user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testGetReviews() throws Exception {

        String uri = "/products/getreviews";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        when(productService.getReviews(1)).thenReturn(getReviewStubData());
        String inputJson = super.mapToJson(1);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user",user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetRatingByUser() throws Exception {
        ArrayList<String> a = new ArrayList<>();
        a.add("Photo1");
        File f = new File("D:/abc/cd.png");
        String uri = "/products/myrating";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        Product product = new Product("Chair","4 legs",5,"furniture",a,"vijay@uncc.edu",f);
        when(productService.getRatingByUser(product, user)).thenReturn(3);
        String inputJson = super.mapToJson(product);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user",user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetAverageRating() throws Exception {
        String uri = "/products/averagerating";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        Product product = new Product();
        when(productService.getAverageRating(product)).thenReturn(3.0);
        String inputJson = super.mapToJson(product);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testAddRating() throws Exception {
        String uri = "/products/addrating";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        Product product = new Product();
        when(productService.addRating(product,user,product.getRating())).thenReturn("Rating added.");
        String inputJson = super.mapToJson(product);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user",user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

}