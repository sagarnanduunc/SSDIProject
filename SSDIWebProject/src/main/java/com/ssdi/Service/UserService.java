package com.ssdi.Service;

import com.ssdi.Dao.IUserDao;
import com.ssdi.Dao.UserDao;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Bank;
import com.ssdi.Entity.IBank;
import com.ssdi.Entity.User;
import com.ssdi.Entity.Payment;
import com.ssdi.Entity.IPayment;
import com.ssdi.Entity.Transaction;
import com.ssdi.Entity.ITransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.text.ParseException;
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

    public Collection<Payment> getPayment(String email){return userDao.getPayment(email);}
    public void addPaymentInfo(Payment payment) { this.userDao.addPaymentInfo(payment); }
    public void addTransactionInfo (Transaction transaction)throws ParseException {this.userDao.addTransactionInfo(transaction);}
}
