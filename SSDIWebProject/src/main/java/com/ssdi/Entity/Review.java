package com.ssdi.Entity;

/**
 * Created by Jacob on 4/21/2017.
 */
public class Review implements IReview {

    private String reviewer;
    private int productId;
    private String description;

    @Override
    public void setReviewer(String email) {
        this.reviewer = email;
    }

    @Override
    public String getReviewer() {
        return reviewer;
    }

    @Override
    public void setProduct(int id) {
        this.productId = id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
