package com.test;

import com.ssdi.Controller.CartController;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import com.ssdi.Service.CartService;
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
 * Created by prayas on 4/9/2017.
 */

//@Transactional
public class CartControllerMocksTest extends AbstractControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private Collection<Product> getCartStubData() {
        Collection<Product> cartProducts = new ArrayList<Product>();
        cartProducts.add(getEntityStubData());
        return cartProducts;
    }

    private Product getEntityStubData() {
        Product product = new Product();
        product.setId(10);
        product.setName("table");
        product.setDescription("good quality table");
        product.setPrice(5);
        product.setEmail("vijay@uncc.edu");
        return product;
    }

    private User getStubData() {
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        return user;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(cartController);
    }

    @Test //
    public void testGetProductsInCart() throws Exception {
        Collection<Product> cartProducts = getCartStubData();
        User user = getStubData();
        when(cartService.getProductsInCart(user)).thenReturn(cartProducts);
        String uri = "/cart/getcart";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testAddProduct() throws Exception {

        String uri = "/cart/addproduct";
        Product product = new Product();
        product.setName("table");
        product.setDescription("good table");
        product.setEmail("vijay@uncc.edu");
        product.setCategory("Game");
        product.setId(2);
        product.setPrice(5);
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
    public void testAddProductNegative() throws Exception {
        User user = getStubData();
        String uri = "/cart/addproduct";
        Product product = new Product();

        String inputJson = super.mapToJson(product);
        when(cartService.addProduct(product, user)).thenReturn("Product added to cart");
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

    @Test //
    public void testCheckoutCart() throws Exception {
        Collection<Product> cartProducts = getCartStubData();
        User user = getStubData();
        when(cartService.checkoutCart(user)).thenReturn(cartProducts);
        String uri = "/cart/checkoutcart";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testRemoveProductNegative() throws Exception {
        User user = getStubData();
        String uri = "/cart/removeproduct";
        Product product = new Product();

        String inputJson = super.mapToJson(product);
        when(cartService.addProduct(product, user)).thenReturn("Product successfully removed");
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

}