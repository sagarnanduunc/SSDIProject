package com.test;

import com.ssdi.Controller.UserController;
import com.ssdi.Dao.ProductDao;
import com.ssdi.Dao.UserDao;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import com.ssdi.Entity.*;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prayas on 3/26/2017.
 */

public class UserDaoMocksTest extends AbstractDaoTest {

    public DataSource getTestDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/rentaltest");
        dataSource.setUsername("root");
        dataSource.setPassword("Password");

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
    public void testingDaoGetAllUsers() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<User> users = userDao.getAllUsers();
        User[]a =users.toArray(new User[0]);
        //System.out.println("Search users : "+a);
        //assertTrue(true);
        //System.out.println("Number of Users: "+users.size());
        //assertEquals(4, users.size());
        assertEquals("jstern5@uncc.edu",a[0].getEmail());
        assertEquals("Jacob",a[0].getFirstName());
        assertEquals("Stern",a[0].getLastName());


    }

    @Test
    public void testingDaoGetUsersByEmailValid() {
        UserDao userDao = new UserDao(getTestDataSource());
        User user = userDao.getUserByEmail("jstern5@uncc.edu");
        //System.out.println("Search users : "+a);
        //assertTrue(true);
        //System.out.println("Number of Users: "+users.size());
        assertEquals("jstern5@uncc.edu",user.getEmail());
        assertEquals("Jacob",user.getFirstName());
        assertEquals("Stern",user.getLastName());
    }

    @Test
    public void testingDaoGetUsersByEmailNull() {
        UserDao userDao = new UserDao(getTestDataSource());
        User user = userDao.getUserByEmail("jstern@uncc.edu");
        assertEquals(null,user);
    }

    @Test
    public void testAddUser() throws Exception {

        String uri = "/users/adduser";
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        UserDao users = new UserDao(getTestDataSource());
        String str=users.addUser(user);
        User a = users.getUserByEmail("vijay@uncc.edu");
        assertEquals(user.getFirstName(),a.getFirstName());
        assertEquals(user.getLastName(),a.getLastName());
        assertEquals("User successfully added",str);
        users.removeUserByEmail("vijay@uncc.edu");

    }

    @Test
    public void testAddUserEmailExists() throws Exception {

        String uri = "/users/adduser";
        User user = new User("Sagar", "Nandu", "snandu1@uncc.edu", "pass");
        UserDao users = new UserDao(getTestDataSource());
        assertEquals("Email id already exists",users.addUser(user));


    }

    @Test
    public void testRemoveUserByEmailValid()
    {
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        UserDao users = new UserDao(getTestDataSource());
        users.addUser(user);
        try{
            users.removeUserByEmail("vijay@uncc.edu");
            User a = users.getUserByEmail("vijay@uncc.edu");
            assertEquals(null,a);
        }catch(Exception e){fail("Test Failed");}

    }

    @Test
    public void testRemoveUserByEmailNull()
    {
        UserDao users = new UserDao(getTestDataSource());
        try{
            users.removeUserByEmail("vijay@uncc.edu");
            fail("Test Failed");
        }catch(Exception e){
            assertEquals("NoSuchUser",e.getMessage());
        }

    }

    @Test
    public void checkLoginTestTrue(){
        UserDao users = new UserDao(getTestDataSource());
        User user = new User("Sagar", "Nandu", "snandu1@uncc.edu", "pass");
        assertTrue(users.checkLogin(user));
    }

    @Test
    public void checkLoginTestFalse(){
        UserDao users = new UserDao(getTestDataSource());
        User user = new User("Vijay", "Chauhan", "vijay@uncc.edu", "dinanath123");
        assertFalse(users.checkLogin(user));
    }

    @Test
    public void addAddressTest()
    {
        UserDao users = new UserDao(getTestDataSource());
        Address address = new Address("street","apartment","city","NC","28262","jstern5@uncc.edu",5);
        users.addAddress(address);
        Collection<Address> addresses = users.getAllAddresses(address.getEmail());
        Address[] addresses1 = addresses.toArray(new Address[0]);
        int len = addresses1.length-1;
        users.removeAddress(addresses1[len].getAddressId());
        assertEquals(address.getApartment(),addresses1[len].getApartment());
        assertEquals(address.getCity(),addresses1[len].getCity());
        assertEquals(address.getState(),addresses1[len].getState());
        assertEquals(address.getStreetAddress(),addresses1[len].getStreetAddress());
        assertEquals(address.getZip(),addresses1[len].getZip());
    }

    @Test
    public void addBankTest()
    {
        UserDao users = new UserDao(getTestDataSource());
        Bank bank = new Bank("bank",1234567890,"Jacob Stern",9876561,"jstern5@uncc.edu",2);
        users.addBankInfo(bank);
        Collection<Bank> banks = users.getAllBankInfo(bank.getEmail());
        Bank[] banks1 = banks.toArray(new Bank[0]);
        int len = banks1.length-1;
        users.removeBankInfo(banks1[len].getBankId());
        assertEquals(bank.getAccountHolderName(),banks1[len].getAccountHolderName());
        assertEquals(bank.getBankName(),banks1[len].getBankName());

        assertEquals(bank.getAccountNumber(),banks1[len].getAccountNumber());
        assertEquals(bank.getRoutingNumber(),banks1[len].getRoutingNumber());
    }

    @Test
    public void getAllAddressTest() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<Address> addresses = userDao.getAllAddresses("snandu1@uncc.edu");
        Address[] addresses1 = addresses.toArray(new Address[0]);

        //assertEquals(3, addresses.size());
        assertEquals("snandu1@uncc.edu",addresses1[0].getEmail());
        assertEquals("dsjksd dffv ",addresses1[0].getStreetAddress());
        assertEquals("sdfs",addresses1[0].getApartment());
        assertEquals("nc",addresses1[0].getState());
        assertEquals("8438",addresses1[0].getZip());

    }

    @Test
    public void getAllBanksTest() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<Bank> banks = userDao.getAllBankInfo("snandu1@uncc.edu");
        Bank[] banks1 = banks.toArray(new Bank[0]);
        //assertEquals(1, banks.size());
        assertEquals("Bank of America ",banks1[0].getBankName());
        assertEquals(984651233,banks1[0].getAccountNumber());
        assertEquals("Sagar",banks1[0].getAccountHolderName());
        assertEquals(651213,banks1[0].getRoutingNumber());

    }

    @Test
    public void getPaymentTest() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<Payment> payments = userDao.getPayment("snandu1@uncc.edu");
        Payment[] payments1 = payments.toArray(new Payment[0]);
        //assertEquals(3, payments.size());
        assertEquals("2564856412347894",payments1[0].getCard_no());
        assertEquals("005",payments1[0].getSecurity_code());
        assertEquals("Sagar Nandu",payments1[0].getName());
        assertEquals("28262",payments1[0].getZip());
        assertEquals(5, payments1[0].getExpmonth());
        assertEquals(2022, payments1[0].getExpyear());
    }

    @Test
    public void addPaymentTest()
    {
        UserDao users = new UserDao(getTestDataSource());
        Payment payment = new Payment("9876543210123456","005","Sagar Nandu","28261",5,2022,"snandu1@uncc.edu");
        users.addPaymentInfo(payment);
        Collection<Payment> payments = users.getPayment(payment.getEmail());
        Payment[] payments1 = payments.toArray(new Payment[0]);
        int len = payments1.length-1;
        users.removePaymentInfo(payments1[len].getId());
        assertEquals(payment.getZip(),payments1[len].getZip());
        assertEquals(payment.getCard_no(),payments1[len].getCard_no());

        assertEquals(payment.getExpmonth(),payments1[len].getExpmonth());
        assertEquals(payment.getExpyear(),payments1[len].getExpyear());
        assertEquals(payment.getName(),payments1[len].getName());
        assertEquals(payment.getSecurity_code(),payments1[len].getSecurity_code());
    }

    @Test
    public void addTransactionTest()throws ParseException
    {
        UserDao users = new UserDao(getTestDataSource());
        Transaction transaction = new Transaction(5,2,1,1,"jstern5@uncc.edu","snandu1@uncc.edu","2017-05-02","2017-06-02");
        users.addTransactionInfo(transaction);
        Collection<Transaction> transactions = users.getRentedProducts(transaction.getEmail_renter());
        Transaction[] transaction1 = transactions.toArray(new Transaction[0]);
        int len = transaction1.length-1;
        users.removeTransaction(transaction1[len].getTransaction_id());
        assertEquals(transaction.getAddress_id(),transaction1[len].getAddress_id());
        assertEquals(transaction.getEmail_rentee(),transaction1[len].getEmail_rentee());
        assertEquals(transaction.getEnd_date(),transaction1[len].getEnd_date());
        assertEquals(transaction.getStart_date(),transaction1[len].getStart_date());
        assertEquals(transaction.getPayment_id(),transaction1[len].getPayment_id());
        assertEquals(transaction.getProduct_id(),transaction1[len].getProduct_id());

    }

    @Test
    public void getRentedProductsTest() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<Transaction> transactions = userDao.getRentedProducts("snandu1@uncc.edu");
        Transaction[] transactions1 = transactions.toArray(new Transaction[0]);
        //assertEquals(1, transactions.size());
        assertEquals("2017-04-21",transactions1[0].getStart_date());
        assertEquals("2017-04-21",transactions1[0].getEnd_date());
        assertEquals("jstern5@uncc.edu",transactions1[0].getEmail_rentee());
        assertEquals(1, transactions1[0].getProduct_id());
        assertEquals(1, transactions1[0].getPayment_id());
        assertEquals(2, transactions1[0].getAddress_id());

    }

    @Test
    public void getProductsRentedOutTest() {
        UserDao userDao = new UserDao(getTestDataSource());
        Collection<Transaction> transactions = userDao.getProductsRentedOut("snandu1@uncc.edu");
        Transaction[] transactions1 = transactions.toArray(new Transaction[0]);
        //assertEquals(1, transactions.size());
        assertEquals("2017-04-20",transactions1[0].getStart_date());
        assertEquals("2017-04-20",transactions1[0].getEnd_date());
        assertEquals("jstern5@uncc.edu",transactions1[0].getEmail_renter());
        assertEquals(3, transactions1[0].getProduct_id());
        assertEquals(4, transactions1[0].getPayment_id());
        assertEquals(3, transactions1[0].getAddress_id());

    }




}