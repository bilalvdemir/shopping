package com.example.shopping.shopping.model;

public class ProductItem extends Product {

    private int quantity;

    public ProductItem(String title, double price, Category category, int quantity) {
        super(title, price, category);
        this.quantity = quantity;
    }

    public ProductItem() {
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductItem [quantity=" + quantity + ", getId()=" + getId() + ", getTitle()=" + getTitle() + ", getPrice()=" + getPrice() + ", getCategory()=" + getCategory() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
    }

}
