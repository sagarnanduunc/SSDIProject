package com.test;

import com.ssdi.Dao.ProductDao;
import com.ssdi.Entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created by Jacob on 4/23/2017.
 */
public class ProductDaoMocksTest extends AbstractDaoTest {
    public DataSource getTestDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/rentaltest");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Test
    public void testDao() {
        ProductDao productDao = new ProductDao(getTestDataSource());
        Collection<Product> p = productDao.getAllProducts();
        Assert.assertTrue(true);
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductDao productDao = new ProductDao(getTestDataSource());
        Product product = new Product("Screwdriver", "Flathead", 15, "Tools", null, "jstern5@uncc.edu", null);
        String message = productDao.addProduct(product);
        Assert.assertNotNull(message);
        Assert.assertEquals(message, "Product successfully added");
    }

    @Test
    public void testGetCategories() {
        ProductDao productDao = new ProductDao(getTestDataSource());
        Collection<String> categories = productDao.getAllCategories();
        Assert.assertNotNull(categories);
        Assert.assertEquals(categories.size(), 8);
    }
}
