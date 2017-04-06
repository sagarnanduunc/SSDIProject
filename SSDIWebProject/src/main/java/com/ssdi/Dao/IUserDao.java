package com.ssdi.Dao;
import com.ssdi.Entity.*;

import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

public interface IUserDao {
    Collection<User> getAllUsers();
    User getUserByEmail(String email);
    void removeUserByEmail(String email);
    void updateUser(User user);
    String addUser(User user);
    boolean checkLogin(User user);
    void addAddress(Address address);
    void addBankInfo(Bank bank);
    Collection<Address> getAllAddresses(String email);
    Collection<Bank> getAllBankInfo(String email);
    public Collection<Payment> getPayment(String email);
    public void addPaymentInfo(Payment payment);
}
