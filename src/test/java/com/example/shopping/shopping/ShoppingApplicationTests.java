package com.example.shopping.shopping;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.shopping.shopping.exception.BadRequestException;
import com.example.shopping.shopping.model.Campaign;
import com.example.shopping.shopping.model.Category;
import com.example.shopping.shopping.model.Coupon;
import com.example.shopping.shopping.model.DiscountType;
import com.example.shopping.shopping.model.Product;
import com.example.shopping.shopping.model.ShoppingCart;
import com.example.shopping.shopping.repository.CampaignRepository;
import com.example.shopping.shopping.repository.CategoryRepository;
import com.example.shopping.shopping.repository.CouponRepository;
import com.example.shopping.shopping.repository.ProductRepository;
import com.example.shopping.shopping.repository.ShoppingCartRepository;
import com.example.shopping.shopping.service.DeliveryCostCalculator;
import com.example.shopping.shopping.service.ShoppingService;

@RunWith(SpringRunner.class)
public class ShoppingApplicationTests {

    ShoppingService        shoppingService;
    @MockBean
    CampaignRepository     campaignRepository;
    @MockBean
    CategoryRepository     categoryRepository;
    @MockBean
    CouponRepository       couponRepository;
    @MockBean
    ProductRepository      productRepository;
    @MockBean
    ShoppingCartRepository cartRepository;

    TestDataGenerator      dataGenerator;

    @Before
    public void setup() {
        shoppingService = new ShoppingService(campaignRepository, categoryRepository, couponRepository, productRepository, cartRepository);
        dataGenerator = new TestDataGenerator();
    }

    @Test
    public void shouldReturnCampaign() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Campaign campaign = dataGenerator.generateCampaignData(10, 2, category, DiscountType.Rate);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(category);
        Mockito.when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);
        Campaign response = shoppingService.createCampaign(campaign);
        Assert.assertEquals(response, campaign);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestExceptionWhenCreateCampaign() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Campaign campaign = dataGenerator.generateCampaignData(10, 2, category, DiscountType.Rate);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(null);
        shoppingService.createCampaign(campaign);
    }

    @Test
    public void shouldReturnCaregory() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category response = shoppingService.createCategory(category);
        Assert.assertEquals(response, category);
    }

    @Test
    public void shouldReturnCategoryWithParent() throws BadRequestException {
        Category parent = dataGenerator.generateCategoryData(1).get(0);
        Category category = dataGenerator.generateCategoryWithParentData(1, parent).get(0);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(parent);
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category response = shoppingService.createCategory(category);
        Assert.assertEquals(response, category);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestExceptionWhenCreateCategory() throws BadRequestException {
        Category parent = dataGenerator.generateCategoryData(1).get(0);
        Category category = dataGenerator.generateCategoryWithParentData(1, parent).get(0);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(null);
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        shoppingService.createCategory(category);
    }

    @Test
    public void shouldReturnCoupon() throws BadRequestException {
        Coupon coupon = dataGenerator.generateCouponData(200, 10, DiscountType.Rate);
        Mockito.when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);
        shoppingService.createCoupon(coupon);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestExceptionWhenCreateCoupon() throws BadRequestException {
        Coupon coupon = dataGenerator.generateCouponData(-1, 10, DiscountType.Rate);
        Mockito.when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);
        shoppingService.createCoupon(coupon);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestException2WhenCreateCoupon() throws BadRequestException {
        Coupon coupon = dataGenerator.generateCouponData(1, 0, DiscountType.Rate);
        Mockito.when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);
        shoppingService.createCoupon(coupon);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestException3WhenCreateCoupon() throws BadRequestException {
        Coupon coupon = dataGenerator.generateCouponData(1, 10, null);
        Mockito.when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);
        shoppingService.createCoupon(coupon);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestException4WhrenCreateCoupon() throws BadRequestException {
        shoppingService.createCoupon(null);
    }

    @Test
    public void shouldReturnProduct() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Product product = dataGenerator.generateProductData("prod1", 10, category);
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(category);
        Product response = shoppingService.createProduct(product);
        Assert.assertEquals(response, product);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestExceptionWhenCreateProduct() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Product product = dataGenerator.generateProductData("prod1", 10, category);
        Mockito.when(categoryRepository.findById(anyString())).thenReturn(null);
        shoppingService.createProduct(product);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestException2WhenCreateProduct() throws BadRequestException {
        shoppingService.createProduct(null);
    }

    @Test
    public void shouldReturnShoppingCart() throws BadRequestException {
        Category category = dataGenerator.generateCategoryData(1).get(0);
        Product product = dataGenerator.generateProductData("prod1", 10, category);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);
        Mockito.when(cartRepository.save(any(ShoppingCart.class))).thenReturn(cart);
        shoppingService.createShoppingCart(cart);
    }

    @Test(expected = BadRequestException.class)
    public void shouldReturnBadRequestExceptionWhenCreateShoppingCart() throws BadRequestException {
        shoppingService.createShoppingCart(null);
    }

    @Test
    public void shouldReturnShoppingCart1() throws BadRequestException {
        List<Category> categoryList = dataGenerator.generateCategoryData(2);
        Category category1 = categoryList.get(0);
        Category category2 = categoryList.get(1);
        Campaign campaign1 = dataGenerator.generateCampaignData(20, 3, category1, DiscountType.Rate);
        Campaign campaign2 = dataGenerator.generateCampaignData(1000, 1, category2, DiscountType.Amount);
        Product product1 = dataGenerator.generateProductData("Apple", 100, category1);
        Product product2 = dataGenerator.generateProductData("Desk", 400, category1);
        Product product3 = dataGenerator.generateProductData("phone", 2000, category2);
        Coupon coupon = dataGenerator.generateCouponData(2000, 10, DiscountType.Rate);
        DeliveryCostCalculator delivery = new DeliveryCostCalculator(5, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product1, 2);
        cart.addItem(product2, 2);
        cart.addItem(product3, 2);
        cart.applyCampaign(campaign1, campaign2);
        cart.applyCoupon(coupon);
        cart.setDeliveryCost(delivery.calculateFor(cart));
        cart.print();
        Double response = cart.getTotalAmountAfterDiscounts() + cart.getDeliveryCost();
        Double required = 3627.99;
        Assert.assertEquals(response, required);
    }
}
