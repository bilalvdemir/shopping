package com.example.shopping.shopping.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ParentCategory implements CategoryInterface {

    @Id
    private String id;
    private String title;

    public ParentCategory(String title) {
        super();
        this.title = title;
    }

    public ParentCategory() {
    }

    public ParentCategory(String id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    @Override
    public Boolean isParent() {
        return true;
    }

}
