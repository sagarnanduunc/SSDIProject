package com.test;

import com.ssdi.Controller.ProductController;
import com.ssdi.Controller.UserController;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
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

        String uri = "/products/addproduct";
        Product product = new Product();
        product.setName("table");
        product.setDescription("good table");
        product.setEmail("vijay@uncc.edu");
        product.setCategory("Game");
        product.setPrice(5);
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

}