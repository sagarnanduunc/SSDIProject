package com.ssdi.Dao;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

public interface IProductDao {
    Collection<Product> getAllProducts();
    Product getProductByName(String name);
    void getProductByCategory(String category);
    void updateProduct(Product product);
}
