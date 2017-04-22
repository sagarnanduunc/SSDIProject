package com.ssdi.Dao;

import com.ssdi.Entity.Category;
import com.ssdi.Entity.PriceRange;
import com.ssdi.Entity.Product;
import com.ssdi.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public Collection<Product> getAvailableProducts() {

        final String sql = "SELECT p.product_id, p.name, p.description, p.price, c.category FROM product p, categories c WHERE p.category_id = c.category_id AND p.status_id=1";
        List<Product> products = jdbcTemplate.query(sql, (resultSet, i) -> {
            Product product = new Product();
            product.setId(resultSet.getInt("product_id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getFloat("price"));
            product.setCategory(resultSet.getString("category"));
            return product;
        });
        final String sql1 = "SELECT product_id, photo_link FROM photo";
        List<Product> a = jdbcTemplate.query(sql1, (resultSet, i) -> {
            int id = resultSet.getInt("product_id");
            for (int j = 0; j < products.size(); j++) {
                if (id == products.get(j).getId()) {
                    products.get(j).setPhotoLink(resultSet.getString("photo_link"));
                }
            }
            return null;
        });


        return products;
    }

    public Collection<Product> getAllProducts() {

        final String sql = "SELECT p.product_id, p.name, p.description, p.price, c.category FROM product p, categories c WHERE p.category_id = c.category_id";
        List<Product> products = jdbcTemplate.query(sql, (resultSet, i) -> {
            Product product = new Product();
            product.setId(resultSet.getInt("product_id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getFloat("price"));
            product.setCategory(resultSet.getString("category"));
            return product;
        });
        final String sql1 = "SELECT product_id, photo_link FROM photo";
        List<Product> a = jdbcTemplate.query(sql1, (resultSet, i) -> {
            int id = resultSet.getInt("product_id");
            for (int j = 0; j < products.size(); j++) {
                if (id == products.get(j).getId()) {
                    products.get(j).setPhotoLink(resultSet.getString("photo_link"));
                }
            }
            return null;
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

            final String sql = "INSERT INTO Product(email,category_id,name,description,price,status_id) values ('" + product.getEmail() + " ', '" + categoryId + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + product.getPrice() + "',1)";
            jdbcTemplate.update(sql);

            final String sql2 = "SELECT product_id, name, description, price FROM product";
            System.out.println("before");
            List<Product> products = jdbcTemplate.query(sql2, (resultSet, i) -> {
                System.out.println("during");
                Product product1 = new Product();
                product1.setId(resultSet.getInt("product_id"));
                return product1;
            });
            System.out.println("after");
            product.setId(products.get(products.size() - 1).getId());
            System.out.println(product.getId());
            final String sql1 = "INSERT INTO Photo(product_id,photo_link) values (" + product.getId() + " , '" + product.getPhotoLink().get(0) + "')";
            System.out.println(sql1);
            jdbcTemplate.update(sql1);

            return "Product successfully added";
        } catch (Exception e) {
            return "Product not added";
        }
    }

    public int getCategoryIdFromName(String categoryName) {
        try {
            final String sql = "SELECT category_id FROM Categories WHERE category = \"" + categoryName + "\"";
            List<Integer> categoryIds = jdbcTemplate.query(sql, (resultSet, i) -> resultSet.getInt("category_id"));
            if (categoryIds.size() != 1) {
                return -1;
            } else {
                return categoryIds.get(0);
            }
        } catch (Exception e) {
            return -2;
        }
//        categoryName = categoryName.toLowerCase();
//        int categoryId = 0;
//        switch (categoryName) {
//            case "clothing":
//                categoryId = 1;
//                break;
//            case "books":
//                categoryId = 2;
//                break;
//            case "movies":
//                categoryId = 3;
//                break;
//            case "games":
//                categoryId = 4;
//                break;
//            case "furniture":
//                categoryId = 5;
//                break;
//            case "technology":
//                categoryId = 6;
//                break;
//            case "tools":
//                categoryId = 7;
//                break;
//            case "other":
//                categoryId = 8;
//                break;
//            default:
//                categoryId = 8;
//        }
//        return categoryId;
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
        final String sql = "SELECT name, description, price FROM product where (description like '%"
                + searchString + "%') or (name like '%" + searchString + "%')";
        return getProductsByQuery(sql);
    }

    public Collection<Product> getProductsByPrice(PriceRange priceRange) {
        final String sql = "SELECT name, description, price FROM product where price between "
                + Float.toString(priceRange.getMinPrice()) + "and " + Float.toString(priceRange.getMaxPrice());
        return getProductsByQuery(sql);
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = new ArrayList<>();
        final String sql = "SELECT category FROM categories";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : list) {
            //System.out.println(row.get("category"));
            categories.add((String) row.get("category"));
        }
        return categories;
    }

    public void changeProductStatus(int id) {
        //System.out.println(id);
        final String sql = "UPDATE product SET status_id=2 WHERE product_id=" + id;
        jdbcTemplate.update(sql);
    }

    @Override
    public String addReview(Review review) {
        try {
            final String sql = "INSERT INTO review(email, review, product_id) VALUES ('"
                    + review.getReviewer() + "', '" + review.getDescription() + "', '" + review.getProductId() + "')";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println("Error is " + e);
            return "There is some problem while adding a review";
        }
        return "Review successfully added";
    }

    @Override
    public Collection<Review> getReviews(int id) {
        final String sql = "SELECT r.review_id, u.firstname, r.review from Review r, User u WHERE r.product_id = " + id + " AND r.email = u.email";
        List<Review> reviews = jdbcTemplate.query(sql, (resultSet, i) -> {
            Review review = new Review();
            review.setReviewer(resultSet.getString("firstname"));
            review.setDescription(resultSet.getString("review"));
            review.setProduct(id);
            return review;
        });
        return reviews;
    }
}