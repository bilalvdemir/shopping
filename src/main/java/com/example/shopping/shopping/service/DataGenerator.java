package com.example.shopping.shopping.service;

import org.springframework.stereotype.Service;

import com.example.shopping.shopping.exception.BadRequestException;
import com.example.shopping.shopping.model.Campaign;
import com.example.shopping.shopping.model.Category;
import com.example.shopping.shopping.model.Coupon;
import com.example.shopping.shopping.model.DiscountType;
import com.example.shopping.shopping.model.Product;
import com.example.shopping.shopping.model.ShoppingCart;

@Service
public class DataGenerator {

    ShoppingService shoppingService;

    public DataGenerator(ShoppingService shoppingService) throws BadRequestException {
        super();
        this.shoppingService = shoppingService;
        generateData();
    }

    public void generateData() throws BadRequestException {

        Category categoryRoot = generateCategoryData("root");
        Category categoryHome = generateCategoryData("home");
        Category categoryElectronics = generateCategoryWithParentData("electronic", categoryRoot);

        Campaign campaignHome = generateCampaignData(categoryHome, 20, 3, DiscountType.Rate);
        Campaign campaignElectronics = generateCampaignData(categoryElectronics, 1000, 1, DiscountType.Amount);
        //campaign applied for root category
        Campaign campaignRoot = generateCampaignData(categoryRoot, 500, 1, DiscountType.Amount);

        Coupon coupon = generateCouponData(2000, 10, DiscountType.Rate);

        Product apple = generateProductData("Apple", 100.0, categoryHome);
        Product desk = generateProductData("Desk", 400.0, categoryHome);
        Product phone = generateProductData("phone", 2000.0, categoryElectronics);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(5, 5);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(apple, 2);
        shoppingCart.addItem(desk, 2);
        shoppingCart.addItem(phone, 2);

        shoppingCart.applyCampaign(campaignHome, campaignElectronics, campaignRoot);
        shoppingCart.applyCoupon(coupon);

        shoppingCart.setDeliveryCost(deliveryCostCalculator.calculateFor(shoppingCart));
        shoppingService.createShoppingCart(shoppingCart).print();
    }

    public Category generateCategoryData(String title) throws BadRequestException {
        Category category = new Category(title);
        return shoppingService.createCategory(category);

    }

    public Category generateCategoryWithParentData(String title, Category parent) throws BadRequestException {
        Category category = new Category(title, parent);
        return shoppingService.createCategory(category);
    }

    public Campaign generateCampaignData(Category category, double discount, int quantity, DiscountType type) throws BadRequestException {
        return shoppingService.createCampaign(new Campaign(category, discount, quantity, type));
    }

    public Coupon generateCouponData(double discountMinPrice, double discount, DiscountType type) throws BadRequestException {
        return shoppingService.createCoupon(new Coupon(discountMinPrice, discount, type));
    }

    public Product generateProductData(String title, double price, Category category) throws BadRequestException {
        return shoppingService.createProduct(new Product(title, price, category));
    }
}
