package com.ssdi.Service;

import com.ssdi.Dao.ICartDao;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Jacob on 4/4/2017.
 */
@Service
public class CartService {

    @Autowired
    @Qualifier("cart")
    private ICartDao cartDao;

    public String addProduct(Product product, User user) {
        return cartDao.addProduct(product, user);
    }

    public Collection<Product> getProductsInCart(User user) {
        return cartDao.getProductsInCart(user);
    }
    public Collection<Product> checkoutCart(User user) {
        return cartDao.checkoutCart(user);
    }

}
