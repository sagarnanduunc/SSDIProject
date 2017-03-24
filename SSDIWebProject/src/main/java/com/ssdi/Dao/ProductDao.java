package com.ssdi.Dao;

import com.ssdi.Entity.Category;
import com.ssdi.Entity.PriceRange;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by prayas on 3/20/2017.
 */

@Repository("product")
public class ProductDao implements IProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Product> getAllProducts() {

        final String sql = "SELECT product_id, name, description, price FROM product";
        List<Product> products = jdbcTemplate.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                return product;
            }
        });
        final String sql1 = "SELECT product_id, photo_link FROM photo";
        List<Product> a=jdbcTemplate.query(sql1, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                int id=resultSet.getInt("product_id");
                for(int j=0;j<products.size();j++)
                {
                    if(id == products.get(j).getId()){
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

        for (Iterator<Category> iterator = category.iterator(); iterator.hasNext();) {
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
    public void addProduct(Product product, User user) {
        String category = product.getCategory();
        category = category.toLowerCase();
        int categoryId = getCategoryIdFromName(category);
        final String sql = "INSERT INTO Product(email,category_id,name,description,price,status_id) values ('"+ user.getEmail()+" ', '"+categoryId+"', '"+ product.getName() + "', '"+product.getDescription()+ "', '"+product.getPrice()+"',1)";
    }

    public int getCategoryIdFromName(String categotyName){
        categotyName = categotyName.toLowerCase();
        int categoryId = 0;
        switch(categotyName){
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

    public List<Product> getProductsByQuery(String query){
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

}