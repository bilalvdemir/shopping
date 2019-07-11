package com.example.shopping.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.shopping.shopping.exception.BadRequestException;
import com.example.shopping.shopping.model.Campaign;
import com.example.shopping.shopping.model.Category;
import com.example.shopping.shopping.model.Coupon;
import com.example.shopping.shopping.model.DiscountType;
import com.example.shopping.shopping.model.Product;

public class TestDataGenerator {

    public List<Category> generateCategoryData(int count) {
        List<Category> list = new ArrayList<Category>();

        for (int i = 1; i <= count; i++) {
            Category category = new Category(generatedStringId(), "category" + i);
            list.add(category);
        }
        return list;
    }

    public List<Category> generateCategoryWithParentData(int count, Category parent) {
        List<Category> list = new ArrayList<Category>();
        for (int i = 1; i <= count; i++) {
            Category category = new Category(generatedStringId(), "category" + i, parent);
            list.add(category);
        }
        return list;
    }

    public Campaign generateCampaignData(double discount, int quantity, Category category, DiscountType type) {
        return new Campaign(generatedStringId(), category, discount, quantity, type);
    }

    public Coupon generateCouponData(double discountMinPrice, double discount, DiscountType type) {
        return new Coupon(generatedStringId(), discountMinPrice, discount, type);
    }

    public Product generateProductData(String title, double price, Category category) throws BadRequestException {
        return new Product(generatedStringId(), title, price, category);
    }

    public String generatedStringId() {
        return UUID.randomUUID().toString();
    }
}
