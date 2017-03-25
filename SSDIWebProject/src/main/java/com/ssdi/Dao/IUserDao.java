package com.ssdi.Dao;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Bank;
import com.ssdi.Entity.IBank;
import com.ssdi.Entity.User;
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
}
