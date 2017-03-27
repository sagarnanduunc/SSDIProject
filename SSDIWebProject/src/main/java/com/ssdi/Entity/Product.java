package com.ssdi.Entity;
import java.io.File;
import java.util.ArrayList;
/**
 * Created by prayas on 3/19/2017.
 */
public class Product implements IProduct{
    private int id;
    private String name;
    private String description;
    private float price;
    private String category;
    private String email;
    private File photo;
    private ArrayList<String> photoLink=new ArrayList<String>();

    public Product(String name, String description, float price, String category, ArrayList<String> photoLink, String email, File photo){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.email = email;
        this.photo = photo;

        for(int i=0;i<photoLink.size();i++)
            this.photoLink.add(photoLink.get(i));
    }

    public Product(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id){this.id=id;}
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

    public ArrayList<String> getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink.add(photoLink);
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public File getPhoto(){
        return this.photo;
    }

}
