package com.ssdi.Dao;

import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jacob on 4/4/2017.
 */
@Repository("cart")
public class CartDao implements ICartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addProduct(Product product, User user) {
        try {
            final String sql = "INSERT INTO Cart(email, product_id) VALUES ('" + user.getEmail() + "', " + product.getId() + ")";
            //System.out.println(sql);
            jdbcTemplate.update(sql);
            return "Product added to cart";
        } catch (DuplicateKeyException e) {
            return "Product already in cart";
        } catch (Exception e) {
            return "Product not added to cart";
        }
    }

    @Override
    public Collection<Product> getProductsInCart(User user) {
        final String sql = "SELECT p.product_id, p.name, p.description, p.price, cat.category, s.status FROM product p, categories cat, cart c, status s WHERE c.email = '"
                + user.getEmail() + "' and p.category_id = cat.category_id and p.product_id = c.product_id and p.status_id = s.status_id";
        List<Product> cart = jdbcTemplate.query(sql, (resultSet, i) -> {
            Product product = new Product();
            product.setId(resultSet.getInt("product_id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getFloat("price"));
            product.setCategory(resultSet.getString("category"));
            product.setStatus(resultSet.getString("status"));
            return product;
        });
        return cart;
    }
}
