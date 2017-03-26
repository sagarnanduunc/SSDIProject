package com.ssdi.Dao;
import java.lang.Exception;
import com.ssdi.Entity.Address;
import com.ssdi.Entity.Bank;
import com.ssdi.Entity.IBank;
import com.ssdi.Entity.User;
import java.util.Collection;

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
 * Created by prayas on 3/20/2017.
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
    public void updateUser(User user) {

    }

    @Override
    public String addUser(User user) throws org.springframework.dao.DuplicateKeyException {
        try {
            final String sql = "INSERT INTO user (email, firstname, lastname, password) VALUES ('" + user.getEmail() + " ', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "')";
            jdbcTemplate.update(sql);
        }
        catch (org.springframework.dao.DuplicateKeyException s){
            return "Email id already exists";
        }

        catch (Exception e){
            return "There is some problem while adding a user";
        }

        return "User successfully added";
    }

    @Override
    public boolean checkLogin(User user){
        final String sql = "SELECT count(*) FROM user WHERE email='"+user.getEmail()+"' AND password='"+user.getPassword()+"'";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count != null && count > 0;
    }

    @Override
    public void addAddress(Address address) {
        final String sql = "INSERT INTO address (email, street_address, apartment, city, state, zip) VALUES ('"+ address.getEmail() + " ', '"+ address.getStreetAddress()+" ', '"+address.getApartment()+"', '"+ address.getCity() + "', '"+address.getState()+ "', '"+address.getZip()+"')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void addBankInfo(Bank bank) {
        final String sql = "INSERT INTO bank_info (email, bank_name, account_number, account_holder_name, routing_number) VALUES ('"+ bank.getEmail() + " ', '"+ bank.getBankName()+" ', '"+bank.getAccountNumber()+"', '"+ bank.getAccountHolderName() + "', '" + bank.getRoutingNumber() +"')";
        jdbcTemplate.update(sql);
    }

    @Override
    public Collection<Address> getAllAddresses(String email) {
        final String sql = "SELECT street_address, apartment, city, state, zip FROM address where email='" +email+"'";
        List<Address> addresses = jdbcTemplate.query(sql, new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet resultSet, int i) throws SQLException {
                Address address = new Address();
                address.setStreetAddress(resultSet.getString("street_address"));
                address.setApartment(resultSet.getString("apartment"));
                address.setCity(resultSet.getString("city"));
                address.setState(resultSet.getString("state"));
                address.setZip(resultSet.getString("zip"));
                return address;
            }
        });
        return addresses;
    }

    @Override
    public Collection<Bank> getAllBankInfo(String email) {
        final String sql = "SELECT bank_name, account_number, account_holder_name, routing_number FROM bank_info where email='" +email+"'";
        List<Bank> banks = jdbcTemplate.query(sql, new RowMapper<Bank>() {
            @Override
            public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
                Bank bank = new Bank();
                bank.setBankName(resultSet.getString("bank_name"));
                bank.setAccountNumber(resultSet.getLong("account_number"));
                bank.setAccountHolderName(resultSet.getString("account_holder_name"));
                bank.setRoutingNumber(resultSet.getLong("routing_number"));
                return bank;
            }
        });
        return banks;
    }

}
