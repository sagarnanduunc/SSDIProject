package com.ssdi.Dao;

import com.ssdi.Entity.Product;

import java.util.Collection;

/**
 * Created by Jacob on 4/4/2017.
 */
public interface ICartDao {
    String addProduct(Product product);

    Collection<Product> getProductsInCart();
}
