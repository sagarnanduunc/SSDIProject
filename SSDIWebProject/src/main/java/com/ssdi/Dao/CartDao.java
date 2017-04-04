package com.ssdi.Dao;

import com.ssdi.Entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Jacob on 4/4/2017.
 */
@Repository("cart")
public class CartDao implements ICartDao {
    @Override
    public String addProduct(Product product) {
        return null;
    }

    @Override
    public Collection<Product> getProductsInCart() {
        return null;
    }
}
