package com.test;
import static org.junit.Assert.*;

import com.ssdi.Entity.Address;
import com.ssdi.Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by prayas on 4/25/2017.
 */

public class AddressEntityTest {
    Address address;
    @Before
    public void setUp() throws Exception {
        address = new Address("508 Barton Creek Dr","I","Charlotte","NC","22331","vijay@uncc.edu", 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetStreetAddress(){
        assertEquals(address.getStreetAddress(), "508 Barton Creek Dr");
    }

    @Test
    public void testApartment(){
        assertEquals(address.getApartment(), "I");
    }

    @Test
    public void testGetCity(){
        assertEquals(address.getCity(), "Charlotte");
    }

    @Test
    public void testGetState(){
        assertEquals(address.getState(), "NC");
    }

    @Test
    public void testGetZip(){
        assertEquals(address.getZip(), "22331");
    }

    @Test
    public void testGetEmail(){
        assertEquals(address.getEmail(), "vijay@uncc.edu");
    }

    @Test
    public void testGetAddressId(){
        address.setAddressId(2);
        assertEquals(address.getAddressId(), 2);
    }

    @Test
    public void testSetStreetAddress(){
        address.setStreetAddress("508 University Terrace Dr");
        assertEquals(address.getStreetAddress(), "508 University Terrace Dr");
    }

    @Test
    public void testSetApartment(){
        address.setApartment("B");
        assertEquals(address.getApartment(), "B");
    }

    @Test
    public void testSetCity(){
        address.setCity("New York");
        assertEquals(address.getCity(), "New York");
    }

    @Test
    public void testSetState(){
        address.setState("SC");
        assertEquals(address.getState(), "SC");
    }

    @Test
    public void testSetZip(){
        address.setZip("11111");
        assertEquals(address.getZip(), "11111");
    }

    @Test
    public void testSetEmail(){
        address.setEmail("dinanath@uncc.edu");
        assertEquals(address.getEmail(), "dinanath@uncc.edu");
    }

    @Test
    public void testSetAddressId(){
        address.setAddressId(2);
        assertEquals(address.getAddressId(), 2);
    }

}
