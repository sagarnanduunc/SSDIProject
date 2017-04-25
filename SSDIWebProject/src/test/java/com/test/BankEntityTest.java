package com.test;
import static org.junit.Assert.*;

import com.ssdi.Entity.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by praya on 4/25/2017.
 */
public class BankEntityTest {
    Bank bank;
    @Before
    public void setUp() throws Exception {
        bank = new Bank("BoFA",1122334433, "Vijay Chauhan", 22334, "vijay@uncc.edu", 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetBankName(){
        assertEquals(bank.getBankName(), "BoFA");
    }

    @Test
    public void testGetAccountNumber(){
        assertEquals(bank.getAccountNumber(), 1122334433);
    }

    @Test
    public void testGetAccountHolderName(){
        assertEquals(bank.getAccountHolderName(), "Vijay Chauhan");
    }

    @Test
    public void testGetRoutingNumber(){
        assertEquals(bank.getRoutingNumber(), 22334);
    }

    @Test
    public void testGetEmail(){
        assertEquals(bank.getEmail(), "vijay@uncc.edu");
    }

    @Test
    public void testGetBankId(){
        assertEquals(bank.getBankId(), 1);
    }

    @Test
    public void testSetBankName(){
        bank.setBankName("State Bank");
        assertEquals(bank.getBankName(), "State Bank");
    }

    @Test
    public void testSetAccountNumber(){
        bank.setAccountNumber(1111111111);
        assertEquals(bank.getAccountNumber(), 1111111111);
    }

    @Test
    public void testSetAccountHolderName(){
        bank.setAccountHolderName("Kancha Cheena");
        assertEquals(bank.getAccountHolderName(), "Kancha Cheena");
    }

    @Test
    public void testSetRoutingNumber(){
        bank.setRoutingNumber(112211);
        assertEquals(bank.getRoutingNumber(), 112211);
    }

    @Test
    public void testSetEmail(){
        bank.setEmail("v@uncc.edu");
        assertEquals(bank.getEmail(), "v@uncc.edu");
    }

    @Test
    public void testSetBankId(){
        bank.setBankId(2);
        assertFalse("bank id is not changed match",bank.getBankId() == 1);
        assertEquals(bank.getBankId(), 2);
    }

}
