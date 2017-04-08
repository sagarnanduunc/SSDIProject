package com.ssdi.Dao;

import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;

import java.util.Collection;

/**
 * Created by Jacob on 4/4/2017.
 */
public interface ICartDao {
    String addProduct(Product product, User user);

    Collection<Product> getProductsInCart(User user);

    Collection<Product> checkoutCart(User user);
}
