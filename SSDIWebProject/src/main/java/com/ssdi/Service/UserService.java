package com.ssdi.Service;

import com.ssdi.Dao.IUserDao;
import com.ssdi.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

@Service
public class UserService {
    @Autowired
    @Qualifier("user")
    private IUserDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    public String addUser(User user) {
        return this.userDao.addUser(user);
    }

    public boolean checkLogin(User user) {
        return this.userDao.checkLogin(user);
    }

    public void addAddress(Address address) {
        this.userDao.addAddress(address);
    }

    public void addBankInfo(Bank bank) {
        this.userDao.addBankInfo(bank);
    }

    public Collection<Address> getAllAddresses(String email) {
        return userDao.getAllAddresses(email);
    }

    public Collection<Bank> getAllBankInfo(String email) {
        return userDao.getAllBankInfo(email);
    }

    public Collection<Payment> getPayment(String email) {
        return userDao.getPayment(email);
    }

    public void addPaymentInfo(Payment payment) {
        this.userDao.addPaymentInfo(payment);
    }

    public void addTransactionInfo(Transaction transaction) throws ParseException {
        this.userDao.addTransactionInfo(transaction);
    }

    public Collection<Transaction> getRentedProducts(String email) {
        return userDao.getRentedProducts(email);
    }

    public Collection<Transaction> getProductsRentedOut(String email) {
        return userDao.getProductsRentedOut(email);
    }

}
