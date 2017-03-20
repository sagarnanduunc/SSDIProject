package com.ssdi.Entity;

/**
 * Created by prayas on 3/19/2017.
 */
public class Product implements IProduct{
    private String name;
    private String description;
    private float price;
    private String category;

    public Product(String name, String description, float price, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
