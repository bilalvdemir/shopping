package com.example.shopping.shopping.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.shopping.shopping.exception.BadRequestException;
import com.example.shopping.shopping.model.Campaign;
import com.example.shopping.shopping.model.Category;
import com.example.shopping.shopping.model.Coupon;
import com.example.shopping.shopping.model.Product;
import com.example.shopping.shopping.model.ShoppingCart;
import com.example.shopping.shopping.repository.CampaignRepository;
import com.example.shopping.shopping.repository.CategoryRepository;
import com.example.shopping.shopping.repository.CouponRepository;
import com.example.shopping.shopping.repository.ProductRepository;
import com.example.shopping.shopping.repository.ShoppingCartRepository;

@Service
public class ShoppingService {

    CampaignRepository     campaignRepository;
    CategoryRepository     categoryRepository;
    CouponRepository       couponRepository;
    ProductRepository      productRepository;
    ShoppingCartRepository cartRepository;

    public ShoppingService(CampaignRepository campaignRepository, CategoryRepository categoryRepository, CouponRepository couponRepository, ProductRepository productRepository, ShoppingCartRepository cartRepository) {
        super();
        this.campaignRepository = campaignRepository;
        this.categoryRepository = categoryRepository;
        this.couponRepository = couponRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public Campaign createCampaign(@Valid Campaign campaign) throws BadRequestException {
        Category category = categoryRepository.findById(campaign.getCategory().getId());
        if (category == null) {
            throw new BadRequestException("Category Not found for create campaign.");
        }
        return campaignRepository.save(campaign);

    }

    public Category createCategory(@Valid Category category) throws BadRequestException {
        if (category.getParentCategory() != null) {
            Category parentCategory = categoryRepository.findById(category.getParentCategory().getId());
            if (parentCategory == null) {
                throw new BadRequestException("Parent Category not found for create category.");
            }
        }
        return categoryRepository.save(category);

    }

    public Coupon createCoupon(@Valid Coupon coupon) throws BadRequestException {
        if (coupon == null || coupon.getType() == null || coupon.getDiscountMinPrice() < 1 || coupon.getDiscount() < 1) {
            throw new BadRequestException("Create coupon request failed. Parameters not valid.");
        }
        return couponRepository.save(coupon);

    }

    public Product createProduct(@Valid Product product) throws BadRequestException {
        if (product == null) {
            throw new BadRequestException("Create product request failed. Category not found.");
        } else {
            Category category = categoryRepository.findById(product.getCategory().getId());
            if (category == null) {
                throw new BadRequestException("Create product request failed. Category not found.");
            }
        }
        return productRepository.save(product);
    }

    public ShoppingCart createShoppingCart(@Valid ShoppingCart shoppingCart) throws BadRequestException {
        if (shoppingCart == null) {

            throw new BadRequestException("Create ShoppingCart request failed.");

        }
        return cartRepository.save(shoppingCart);
    }

}
