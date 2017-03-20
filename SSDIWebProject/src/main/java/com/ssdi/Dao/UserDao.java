package com.ssdi.Dao;
import com.ssdi.Entity.User;
import java.util.Collection;

import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by praya on 3/20/2017.
 */

@Repository("user")
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<User> getAllUsers() {
        final String sql = "SELECT email, firstname, lastname FROM user";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                return user;
            }
        });
        return users;
    }


    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void removeUserByEmail(String email) {

    }

    @Override
    public void updateStudent(User user) {

    }
}
