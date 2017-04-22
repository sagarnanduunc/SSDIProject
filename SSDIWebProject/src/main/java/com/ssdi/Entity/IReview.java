package com.ssdi.Entity;

/**
 * Created by Jacob on 4/20/2017.
 */
public interface IReview {

    void setReviewer(String reviewer);

    String getReviewer();

    void setProduct(int id);

    int getProductId();

    void setDescription(String description);

    String getDescription();
}
