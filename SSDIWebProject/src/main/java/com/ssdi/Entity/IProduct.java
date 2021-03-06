package com.ssdi.Entity;

/**
 * Created by prayas on 3/20/2017.
 */
public interface IProduct {
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    float getPrice();

    void setPrice(float price);

    String getCategory();

    void setCategory(String category);

    String getEmail();

    void setEmail(String email);

    String getStatus();

    void setStatus(String status);

    void setRating(int rating);

    int getRating();
}
