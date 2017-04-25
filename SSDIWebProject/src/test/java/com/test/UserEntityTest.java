package com.test;
import static org.junit.Assert.*;

import com.ssdi.Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by praya on 4/25/2017.
 */
public class UserEntityTest {
    User user;
    @Before
    public void setUp() throws Exception {
        user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "Dinanath@123");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetFirstName(){
        assertEquals(user.getFirstName(), "Vijay");
    }

    @Test
    public void testGetLastName(){
        assertEquals(user.getLastName(), "Chauhan");
    }

    @Test
    public void testGetEmail(){
        assertEquals(user.getEmail(), "vijay@uncc.edu");
    }

    @Test
    public void testGetPassword(){
        assertEquals(user.getPassword(), "Dinanath@123");
    }

    @Test
    public void testSetFirstName(){
        user.setFirstName("Kanchare");
        assertEquals(user.getFirstName(), "Kanchare");
    }

    @Test
    public void testSetLastName(){
        user.setLastName("China");
        assertEquals(user.getLastName(), "China");
    }

    @Test
    public void testSetPassword(){
        user.setPassword("Mandwa@456");
        assertEquals(user.getPassword(), "Mandwa@456");
    }

    @Test
    public void testSetEmail(){
        user.setEmail("kanchare@uncc.edu");
        assertEquals(user.getEmail(), "kanchare@uncc.edu");
    }

}
