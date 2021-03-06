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

    Collection<Product> getAvailableProducts();

    String addReview(Review review);

    Collection<Review> getReviews(int id);

    int getRatingByUser(Product product, User user);

    double getAverageRating(Product product);

    String addRating(Product product, User user, int rating);
}
