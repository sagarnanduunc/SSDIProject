package com.ssdi.Dao;

import com.ssdi.Entity.*;

import java.text.ParseException;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

public interface IUserDao {
    Collection<User> getAllUsers();

    User getUserByEmail(String email);

    void removeUserByEmail(String email)throws Exception;

    void updateUser(User user);

    String addUser(User user);

    boolean checkLogin(User user);

    void addAddress(Address address);
    void removeAddress(int id);
    void addBankInfo(Bank bank);
    void removeBankInfo(int id);
    Collection<Address> getAllAddresses(String email);

    Collection<Bank> getAllBankInfo(String email);

    Collection<Payment> getPayment(String email);

    void addPaymentInfo(Payment payment);
    void removePaymentInfo(int id);
    void addTransactionInfo(Transaction transaction) throws ParseException;
    void removeTransaction(int id);
    Collection<Transaction> getRentedProducts(String email);

    Collection<Transaction> getProductsRentedOut(String email);
}
