package com.ssdi.Entity;

/**
 * Created by Jacob on 4/21/2017.
 */
public class Review implements IReview {

    private String reviewer;
    private int productId;
    private String description;

    public Review(String reviewer, int productId, String description){
        this.reviewer = reviewer;
        this.productId = productId;
        this.description = description;
    }

    public Review(){}

    @Override
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
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
