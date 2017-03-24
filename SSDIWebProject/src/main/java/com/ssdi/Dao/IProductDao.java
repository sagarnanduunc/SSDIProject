package com.ssdi.Dao;
import com.ssdi.Entity.Category;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Created by prayas on 3/20/2017.
 */

public interface IProductDao {
    Collection<Product> getAllProducts();
    Collection<Product> getProductByName(String name);
    Collection<Product> getProductsByCategory(Collection<Category> category);
    void updateProduct(Product product);
    void addProduct(Product product, User user);
    Collection<Product> searchProductsByString(String searchString);
}
