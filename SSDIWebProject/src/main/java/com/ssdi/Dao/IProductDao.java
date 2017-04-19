package com.ssdi.Dao;

import com.ssdi.Entity.*;

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

    String addProduct(Product product);

    Collection<Product> searchProductsByString(String searchString);

    Collection<Product> getProductsByPrice(PriceRange priceRange);

    ArrayList<String> getAllCategories();

    void changeProductStatus(int id);

    String addReview(Review review);
}
