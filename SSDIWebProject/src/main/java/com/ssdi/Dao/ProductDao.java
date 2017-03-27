package com.ssdi.Dao;

import com.ssdi.Entity.Category;
import com.ssdi.Entity.PriceRange;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by prayas on 3/20/2017.
 */

@Repository("product")
public class ProductDao implements IProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Product> getAllProducts() {

        final String sql = "SELECT p.product_id, p.name, p.description, p.price, c.category FROM product p, categories c WHERE p.category_id = c.category_id";
        List<Product> products = jdbcTemplate.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setCategory(resultSet.getString("category"));
                return product;
            }
        });
        final String sql1 = "SELECT product_id, photo_link FROM photo";
        List<Product> a = jdbcTemplate.query(sql1, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("product_id");
                for (int j = 0; j < products.size(); j++) {
                    if (id == products.get(j).getId()) {
                        products.get(j).setPhotoLink(resultSet.getString("photo_link"));
                    }
                }
                return null;
            }
        });


        return products;
    }


    @Override
    public Collection<Product> getProductByName(String name) {
        return null;
    }

    public Collection<Product> getProductsByCategory(Collection<Category> category) {
        String categoryIds = "";

        for (Iterator<Category> iterator = category.iterator(); iterator.hasNext(); ) {
            categoryIds = categoryIds + Integer.toString(getCategoryIdFromName(iterator.next().getCategory())) + ",";
        }
        categoryIds = categoryIds.substring(0, categoryIds.length() - 1);
        final String sql = "SELECT name, description, price FROM product where category_id in (" + categoryIds + ")";
        return getProductsByQuery(sql);
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public String addProduct(Product product) {
        try {
            String category = product.getCategory();
            category = category.toLowerCase();
            int categoryId = getCategoryIdFromName(category);
            File f = new File("C:\\photo\\"+product.getName()+product.getDescription()+".jpg");
            //byte[] bytes = product.getPhoto().getBytes();
            //File photo =
            f = product.getPhoto();
            final String sql = "INSERT INTO Product(email,category_id,name,description,price,status_id) values ('" + product.getEmail() + " ', '" + categoryId + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + product.getPrice() + "',1)";
            jdbcTemplate.update(sql);
            return "Product successfully added";
        }
        catch (Exception e){
            return "Product not added";
        }
    }

    public int getCategoryIdFromName(String categotyName) {
        categotyName = categotyName.toLowerCase();
        int categoryId = 0;
        switch (categotyName) {
            case "clothing":
                categoryId = 1;
                break;
            case "books":
                categoryId = 2;
                break;
            case "movies":
                categoryId = 3;
                break;
            case "games":
                categoryId = 4;
                break;
            case "furniture":
                categoryId = 5;
                break;
            case "technology":
                categoryId = 6;
                break;
            case "tools":
                categoryId = 7;
                break;
            case "other":
                categoryId = 8;
                break;
            default:
                categoryId = 8;
        }
        return categoryId;
    }

    public List<Product> getProductsByQuery(String query) {
        List<Product> products = jdbcTemplate.query(query, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                return product;
            }
        });
        return products;
    }

    //Search a product with string which completely or partially matches product name or description
    public Collection<Product> searchProductsByString(String searchString) {
        final String sql = "SELECT name, description, price FROM product where (description like '%" + searchString + "%') or (name like '%" + searchString + "%')";
        return getProductsByQuery(sql);
    }

    public Collection<Product> getProductsByPrice(PriceRange priceRange) {
        final String sql = "SELECT name, description, price FROM product where price between " + Float.toString(priceRange.getMinPrice()) + "and " + Float.toString(priceRange.getMaxPrice());
        return getProductsByQuery(sql);
    }

    public ArrayList<String> getAllCategories() {

        ArrayList<String> categories = new ArrayList<String>();
        final String sql = "SELECT category from categories";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : list) {
            System.out.println(row.get("category"));
            categories.add((String) row.get("category"));
        }
        return categories;
    }
}