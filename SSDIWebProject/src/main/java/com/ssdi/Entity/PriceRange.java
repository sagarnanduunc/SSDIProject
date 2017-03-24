package com.ssdi.Entity;

/**
 * Created by praya on 3/24/2017.
 */
public class PriceRange implements IPriceRange {

    float minPrice;
    float maxPrice;

    public PriceRange(float minPrice, float maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public PriceRange() {
    }

    @Override
    public float getMinPrice() {
        return minPrice;
    }

    @Override
    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public float getMaxPrice() {
        return maxPrice;
    }

    @Override
    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
}
