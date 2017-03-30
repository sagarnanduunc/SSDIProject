package com.test;

import com.ssdi.Controller.UserController;
import com.ssdi.Dao.UserDao;
import com.ssdi.Entity.Address;
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

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prayas on 3/26/2017.
 */

//@Transactional
public class UserDaoMocksTest extends AbstractDaoTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private UserDao userDao;

    private Collection<User> getUserStubData() {
        Collection<User> users = new ArrayList<User>();
        users.add(getEntityStubData());
        return users;
    }

    private User getEntityStubData() {
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        return user;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(userDao);
    }

    @Test //
    public void testGetAllUsers() throws Exception {
        Collection<User> users = userDao.getAllUsers();
        System.out.println("Collection of users: "+ users);
        Assert.assertTrue(true);

    }

    @Test
    public void testAddUser() throws Exception {

        String uri = "/users/adduser";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(user);

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
    public void testLogin() throws Exception {
        String uri = "/users/login";
        User user = new User();
        user.setEmail("prayas@uncc.edu");
        user.setPassword("abcdfg@1234");

        String inputJson = super.mapToJson(user);

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