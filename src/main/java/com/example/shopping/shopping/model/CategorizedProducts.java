package com.example.shopping.shopping.model;

import java.util.Set;

import org.springframework.data.annotation.Id;

public class CategorizedProducts {

    @Id
    private String               id;
    private CategoryInterface category;
    private Set<ProductItem>  products;
    private int               productsCount;

    public CategorizedProducts(CategoryInterface category, Set<ProductItem> products, int productsCount) {
        super();
        this.category = category;
        this.products = products;
        this.productsCount = productsCount;
    }

    public CategorizedProducts() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public CategoryInterface getCategory() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }
       

    public void setCategory(CategoryInterface category) {
        this.category = category;
    }

    public Set<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductItem> products) {
        this.products = products;
    }

    public void addProduct(ProductItem products) {
        this.products.add(products);
        this.productsCount += products.getQuantity();
    }

    public void removeProduct(ProductItem products) {
        this.products.remove(products);
        this.productsCount -= products.getQuantity();
    }

    @Override
    public String toString() {
        return "CategorizedProducts [id=" + id + ", category=" + category + ", products=" + products + "]";
    }

}
