package com.ssdi.Dao;

import com.ssdi.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * Created by prayas on 3/20/2017.
 */

@Repository("user")
@Transactional
public class UserDao implements IUserDao {

    DataConnection d = new DataConnection();

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(d.getDataSource());

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Collection<User> getAllUsers() {
        final String sql = "SELECT email, firstname, lastname FROM user";
        List<User> users = jdbcTemplate.query(sql, (resultSet, i) -> {
            User user = new User();
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            return user;
        });
        if(users.size()==0)
            return null;
        return users;
    }


    @Override
    public User getUserByEmail(String email) {
        final String sql = "SELECT * FROM user WHERE email='"+email+"'";
        List<User> users = jdbcTemplate.query(sql, (resultSet, i) -> {
            User user = new User();
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            return user;
        });
        if(users.size()==0)
            return null;
        return users.get(0);
    }

    @Override
    public void removeUserByEmail(String email) throws Exception{
        if(getUserByEmail(email)==null)
            throw new Exception("NoSuchUser");
        final String sql = "DELETE FROM user WHERE email='"+email+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public String addUser(User user) throws org.springframework.dao.DuplicateKeyException {
        try {
            final String sql = "INSERT INTO user (email, firstname, lastname, password) VALUES ('"
                    + user.getEmail() + " ', '" + user.getFirstName() + "', '" + user.getLastName()
                    + "', '" + user.getPassword() + "')";
            jdbcTemplate.update(sql);
        } catch (org.springframework.dao.DuplicateKeyException s) {
            return "Email id already exists";
        } catch (Exception e) {
            return "There is some problem while adding a user";
        }
        return "User successfully added";
    }

    @Override
    public boolean checkLogin(User user) {
        final String sql = "SELECT count(*) FROM user WHERE email='" + user.getEmail() + "' AND password='"
                + user.getPassword() + "'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public void addAddress(Address address) {
        final String sql = "INSERT INTO address (email, street_address, apartment, city, state, zip) VALUES ('" + address.getEmail() + " ', '" + address.getStreetAddress() + "', '" + address.getApartment() + "', '" + address.getCity() + "', '" + address.getState() + "', '" + address.getZip() + "')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void removeAddress(int id) {
        final String sql = "DELETE FROM address WHERE address_id="+id;
        jdbcTemplate.update(sql);
    }

    @Override
    public void addBankInfo(Bank bank) {
        final String sql = "INSERT INTO bank_info (email, bank_name, account_number, account_holder_name, routing_number) VALUES ('" + bank.getEmail() + "', '" + bank.getBankName() + "', '" + bank.getAccountNumber() + "', '" + bank.getAccountHolderName() + "', '" + bank.getRoutingNumber() + "')";
        jdbcTemplate.update(sql);
    }
    @Override
    public void removeBankInfo(int id) {
        final String sql = "DELETE FROM bank_info WHERE bank_info_id="+id;
        jdbcTemplate.update(sql);
    }

    @Override
    public Collection<Address> getAllAddresses(String email) {
        final String sql = "SELECT address_id, street_address, apartment, city, state, zip FROM address where email='" + email + "'";
        List<Address> addresses = jdbcTemplate.query(sql, (resultSet, i) -> {
            Address address = new Address();
            address.setAddressId(resultSet.getInt("address_id"));
            address.setEmail(email);
            address.setStreetAddress(resultSet.getString("street_address"));
            address.setApartment(resultSet.getString("apartment"));
            address.setCity(resultSet.getString("city"));
            address.setState(resultSet.getString("state"));
            address.setZip(resultSet.getString("zip"));
            return address;
        });
        return addresses;
    }

    @Override
    public Collection<Bank> getAllBankInfo(String email) {
        final String sql = "SELECT bank_info_id, bank_name, account_number, account_holder_name, routing_number FROM bank_info where email='" + email + "'";
        List<Bank> banks = jdbcTemplate.query(sql, (resultSet, i) -> {
            Bank bank = new Bank();
            bank.setBankId(resultSet.getInt("bank_info_id"));
            bank.setBankName(resultSet.getString("bank_name"));
            bank.setAccountNumber(resultSet.getLong("account_number"));
            bank.setAccountHolderName(resultSet.getString("account_holder_name"));
            bank.setRoutingNumber(resultSet.getLong("routing_number"));
            return bank;
        });
        return banks;
    }

    @Override
    public Collection<Payment> getPayment(String email) {
        final String sql = "SELECT payment_id, card_no, security_key, name, zip, expmonth, expyear FROM Payment where email='" + email + "'";
        List<Payment> payments = jdbcTemplate.query(sql, (resultSet, i) -> {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("payment_id"));
            payment.setName(resultSet.getString("name"));
            payment.setCard_no(resultSet.getString("card_no"));
            payment.setSecurity_code(resultSet.getString("security_key"));
            payment.setMonth(resultSet.getInt("expmonth"));
            payment.setYear(resultSet.getInt("expyear"));
            payment.setZip(resultSet.getString("zip"));
            return payment;
        });
        return payments;
    }

    @Override
    public void addPaymentInfo(Payment payment) {
        final String sql = "INSERT INTO payment (email, card_no, security_key, name, zip, expmonth, expyear) VALUES ('"
                + payment.getEmail() + " ', '" + payment.getCard_no() + " ', '" + payment.getSecurity_code() + "', '"
                + payment.getName() + "', '" + payment.getZip() + "'," + payment.getExpmonth() + ","
                + payment.getExpyear() + ")";
        jdbcTemplate.update(sql);
    }

    @Override
    public void removePaymentInfo(int id) {
        final String sql = "DELETE FROM payment WHERE payment_id="+id;
        jdbcTemplate.update(sql);
    }

    @Override
    public void addTransactionInfo(Transaction transaction) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilStartDate = format.parse(transaction.getStart_date());
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            java.util.Date utilEndDate = format.parse(transaction.getEnd_date());
            java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
            System.out.println(sqlStartDate);
            final String sql = "INSERT INTO transaction(email_renter, email_rentee, start_date, end_date, product_id, payment_id, address_id) values('"
                    + transaction.getEmail_renter() + "','" + transaction.getEmail_rentee() + "','"
                    + sqlStartDate + "','" + sqlEndDate + "','" + transaction.getProduct_id() + "','"
                    + transaction.getPayment_id() + "','" + transaction.getAddress_id() + "')";
            jdbcTemplate.update(sql);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(transaction.getStart_date());
//        System.out.println(transaction.getAddress_id());
//        System.out.println(transaction.getEmail_rentee());
//        System.out.println(transaction.getEmail_renter());
//        System.out.println(transaction.getProduct_id());
    }

    @Override
    public void removeTransaction(int id) {
        final String sql = "DELETE FROM transaction WHERE transaction_id="+id;
        jdbcTemplate.update(sql);
    }

    @Override
    public Collection<Transaction> getRentedProducts(String email) {
        final String sql = "SELECT * FROM transaction where email_renter='" + email + "'";
        List<Transaction> transactions = jdbcTemplate.query(sql, (resultSet, i) -> {
            Transaction transaction = new Transaction();
            transaction.setAddress_id(resultSet.getInt("address_id"));
            transaction.setEmail_renter(resultSet.getString("email_renter"));
            transaction.setEmail_rentee(resultSet.getString("email_rentee"));
            transaction.setEnd_date(resultSet.getString("end_date"));
            transaction.setPayment_id(resultSet.getInt("payment_id"));
            transaction.setProduct_id(resultSet.getInt("product_id"));
            transaction.setStart_date(resultSet.getString("start_date"));
            transaction.setTransaction_id(resultSet.getInt("transaction_id"));
            return transaction;
        });
        return transactions;
    }

    public Collection<Transaction> getProductsRentedOut(String email) {
        final String sql = "SELECT * FROM transaction where email_rentee='" + email + "'";
        List<Transaction> transactions = jdbcTemplate.query(sql, (resultSet, i) -> {
            Transaction transaction = new Transaction();
            transaction.setAddress_id(resultSet.getInt("address_id"));
            transaction.setEmail_renter(resultSet.getString("email_renter"));
            transaction.setEmail_rentee(resultSet.getString("email_rentee"));
            transaction.setEnd_date(resultSet.getString("end_date"));
            transaction.setPayment_id(resultSet.getInt("payment_id"));
            transaction.setProduct_id(resultSet.getInt("product_id"));
            transaction.setStart_date(resultSet.getString("start_date"));
            transaction.setTransaction_id(resultSet.getInt("transaction_id"));
            return transaction;
        });
        return transactions;
    }

}
