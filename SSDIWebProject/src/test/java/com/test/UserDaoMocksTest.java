package com.test;

import com.ssdi.Controller.UserController;
import com.ssdi.Dao.ProductDao;
import com.ssdi.Dao.UserDao;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import com.ssdi.Service.ProductService;
import com.ssdi.Service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prayas on 3/26/2017.
 */

//@Transactional
public class UserDaoMocksTest extends AbstractDaoTest {

    public DataSource getTestDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/rentaltest");
        dataSource.setUsername("root");
        dataSource.setPassword("Prayas123!");

        return dataSource;
    }

//    @InjectMocks
//    private UserDao userDao;

    private Collection<User> getUserStubData() {
        Collection<User> users = new ArrayList<User>();
        users.add(getEntityStubData());
        return users;
    }

    private User getEntityStubData() {
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        return user;
    }

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        setUp(userDao);
//    }

//    @Test //
//    public void testGetAllUsers() throws Exception {
//        List<User> users;
//        when(jdbcTemplate.query(anyString(),any(RowMapper.class), Matchers.<User> any()).thenReturn(anyList()));
//
//        Collection<User> retVal = UserDao.getAllUsers();
//        assertEquals("bob", retVal);
//        Collection<User> testUsers = userDao.getAllUsers();
//        System.out.println("Collection of users: "+ testUsers);
//        Assert.assertTrue(true);
//
//    }

    @Test
    public void testingDao() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<User> a = userDao.getAllUsers();
        System.out.println("Search users : "+a);
        assertTrue(true);
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

        assertEquals("failure - expected HTTP status 200", 200, status);
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

        assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

}