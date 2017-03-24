package com.ssdi.Entity;

/**
 * Created by prayas on 3/23/2017.
 */
public class Category implements ICategory{

    int categoryId;
    String category;

    Category(String category, int categoryId){
        this.category = category;
        this.categoryId = categoryId;
    }

    Category(){}

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
