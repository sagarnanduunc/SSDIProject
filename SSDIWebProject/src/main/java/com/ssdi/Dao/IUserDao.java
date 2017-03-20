package com.ssdi.Dao;
import com.ssdi.Entity.User;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

public interface IUserDao {
    Collection<User> getAllUsers();
    User getUserByEmail(String email);
    void removeUserByEmail(String email);
    void updateStudent(User user);
}
