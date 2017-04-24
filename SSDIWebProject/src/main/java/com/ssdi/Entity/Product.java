package com.ssdi.Entity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by prayas on 3/19/2017.
 */
public class Product implements IProduct {
    private int id;
    private String name;
    private String description;
    private float price;
    private String category;
    private String email;
    private String status;
    private File photo;
    private ArrayList<String> photoLink = new ArrayList<>();
    private int rating;

    public Product(String name, String description, float price, String category, ArrayList<String> photoLink, String email, File photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.email = email;
        this.photo = photo;
        this.photoLink.addAll(photoLink);
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink.add(photoLink);
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public File getPhoto() {
        return this.photo;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }

}
