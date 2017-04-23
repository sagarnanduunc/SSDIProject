package com.test;

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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prayas on 3/26/2017.
 */

//@Transactional
public class UserControllerMocksTest extends AbstractControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private UserController userController;

    private Collection<User> getUserStubData() {
        Collection<User> users = new ArrayList<User>();
        users.add(getEntityStubData());
        return users;
    }

    private User getEntityStubData() {
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        return user;
    }

    private Collection<Bank> getBankStubData() {
        Collection<Bank> banks = new ArrayList<Bank>();
        Bank bank = new Bank("BofA", 123456789, "Vijay Chauhan", 22334422, "vijay@uncc.edu",2);
        banks.add(bank);
        return banks;
    }

    private Collection<Address> getAddressStubData() {
        Collection<Address> addresses = new ArrayList<Address>();
        Address address = new Address("502 Barton Creek Dr", "B", "Charlotte", "NC", "23343","vijay@uncc.edu",2);
        addresses.add(address);
        return addresses;
    }

    private Collection<Payment> getPaymentStubData() {
        Collection<Payment> payments = new ArrayList<Payment>();
        Payment payment = new Payment("5678123443213456","223","Vijay Chauhan","28263",10,2019,"vijay@uncc.edu");
        payments.add(payment);
        return payments;
    }

    private Collection<Transaction> getTransactionStubData() {
        Collection<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction(2,2,3,1,"vijay@uncc.edu","prayas@uncc.edu","12/12/2017","01/03/2018");
        transactions.add(transaction);
        return transactions;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(userController);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Collection<User> users = getUserStubData();
        when(userService.getAllUsers()).thenReturn(users);
        String uri = "/users";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getAllUsers();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
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

    @Test
    public void testLogout() throws Exception {
        String uri = "/users/logout";
        User user = new User();
        user.setEmail("vijay@uncc.edu");
        user.setPassword("abcdfg@1234");

        String inputJson = super.mapToJson(user);

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
    public void testGetAllBankInfo() throws Exception {
        Collection<Bank> banks = getBankStubData();
        User u = new User("Vijay", "Chauhan", "vijay@uncc.edu", "vijay@1234");
        //when((User)httpSession.getAttribute("user")).thenReturn(u);
        when(userService.getAllBankInfo(u.getEmail())).thenReturn(banks);
        String uri = "/users/bankinfo";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .sessionAttr("user",u)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getAllBankInfo("vijay@uncc.edu");
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetAllAddresses() throws Exception {
        Collection<Address> addresses = getAddressStubData();
        User u = new User("Vijay", "Chauhan", "vijay@uncc.edu", "vijay@1234");
        when(userService.getAllAddresses(u.getEmail())).thenReturn(addresses);
        String uri = "/users/address";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .sessionAttr("user",u)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getAllAddresses("vijay@uncc.edu");
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testPaymentInfo() throws Exception {
        Collection<Payment> payments = getPaymentStubData();
        User u = new User("Vijay", "Chauhan", "vijay@uncc.edu", "vijay@1234");
        when(userService.getPayment(u.getEmail())).thenReturn(payments);
        String uri = "/users/paymentinfo";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .sessionAttr("user",u)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getPayment("vijay@uncc.edu");
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetRentedProducts() throws Exception {
        Collection<Transaction> transactions = getTransactionStubData();
        User u = new User("Vijay", "Chauhan", "vijay@uncc.edu", "vijay@1234");
        when(userService.getRentedProducts(u.getEmail())).thenReturn(transactions);
        String uri = "/users/rentedproducts";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .sessionAttr("user",u)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getRentedProducts("vijay@uncc.edu");
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetProductsRentedOut() throws Exception {
        Collection<Transaction> transactions = getTransactionStubData();
        User u = new User("Vijay", "Chauhan", "vijay@uncc.edu", "vijay@1234");
        when(userService.getProductsRentedOut(u.getEmail())).thenReturn(transactions);
        String uri = "/users/productsrentedout";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .sessionAttr("user",u)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(userService, times(1)).getProductsRentedOut("vijay@uncc.edu");
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testAddAddress() throws Exception {

        String uri = "/users/addaddress";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(user);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testAddBankInfo() throws Exception {

        String uri = "/users/addbankinfo";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(user);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testAddPaymentInfo() throws Exception {

        String uri = "/users/addpaymentinfo";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");

        String inputJson = super.mapToJson(user);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

    @Test
    public void testAddTransactionInfo() throws Exception {
        Transaction transaction = new Transaction(2,2,3,1,"vijay@uncc.edu","prayas@uncc.edu","12/12/2017","01/03/2018");
        String uri = "/users/addtransactioninfo";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        //when(userService.addTransactionInfo(transaction)).getMock();
        String inputJson = super.mapToJson(user);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .sessionAttr("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
    }

}