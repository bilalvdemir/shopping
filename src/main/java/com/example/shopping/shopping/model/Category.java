package com.example.shopping.shopping.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Category implements CategoryInterface {

    @Id
    private String         id;
    @NotNull
    private String         title;
    private ParentCategory parentCategory;

    public Category(String id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Category(String id, String title, Category category) {
        super();
        this.id = id;
        this.title = title;
        this.parentCategory = new ParentCategory(category.getId(), category.getTitle());
    }

    public Category(String title, Category category) {
        super();
        this.title = title;
        this.parentCategory = new ParentCategory(category.getId(), category.getTitle());
    }

    public Category(String title) {
        super();
        this.title = title;
    }

    public Category() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ParentCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", title=" + title + ", parentCategory=" + parentCategory + "]";
    }

    @Override
    public Boolean isParent() {
        return false;
    }

}
