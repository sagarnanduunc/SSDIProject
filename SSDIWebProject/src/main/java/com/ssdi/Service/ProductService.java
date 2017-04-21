package com.ssdi.Service;

import com.ssdi.Dao.IProductDao;
import com.ssdi.Entity.Category;
import com.ssdi.Entity.PriceRange;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

@Service
public class ProductService {

    @Autowired
    @Qualifier("product")
    private IProductDao productDao;

    public Collection<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Collection<Product> getAvailableProducts() {
        return productDao.getAvailableProducts();
    }

    public Collection<Product> getProductsByCategory(Collection<Category> category) {
        return productDao.getProductsByCategory(category);
    }

    public Collection<Product> searchProducts(String searchString) {
        return productDao.searchProductsByString(searchString);
    }

    public Collection<Product> getProductsByPrice(PriceRange priceRange) {
        return productDao.getProductsByPrice(priceRange);
    }

    public ArrayList<String> getAllCategories() {
        return productDao.getAllCategories();
    }

    public String addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public void changeProductStatus(int id) {
        this.productDao.changeProductStatus(id);
    }

    public String addReview(Review review) {
        return this.productDao.addReview(review);
    }

}
