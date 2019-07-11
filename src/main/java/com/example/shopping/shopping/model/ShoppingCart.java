package com.example.shopping.shopping.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;

import com.example.shopping.shopping.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCart {

    @Id
    private String                   id;
    private Set<CategorizedProducts> products;
    private double                   cost;
    private double                   afterDiscountsCost;
    private double                   couponDiscount;
    private double                   campaignDiscount;
    private double                   deliveryCost;

    public void addItem(Product product, int quantity) {
        if (products == null) {
            products = new HashSet<>();
        }
        Set<CategorizedProducts> filtered = products.stream().filter(categorizedProducts -> {
            if (categorizedProducts.getCategory().getId() == product.getCategory().getId() || (product.getCategory().getParentCategory() != null && categorizedProducts.getCategory().getId() == product.getCategory().getParentCategory().getId())) {
                return true;
            } else
                return false;
        }).collect(Collectors.toSet());
        if (filtered.size() > 0) {
            // update ediyomu test et
            filtered.iterator().forEachRemaining(filteredItem -> filteredItem.addProduct(new ProductItem(product.getTitle(), product.getPrice(), product.getCategory(), quantity)));
        } else {

            products.add(new CategorizedProducts(product.getCategory(), new HashSet<ProductItem>(Arrays.asList(new ProductItem(product.getTitle(), product.getPrice(), product.getCategory(), quantity))), quantity));
            if (product.getCategory().getParentCategory() != null) {
                products.add(new CategorizedProducts(product.getCategory().getParentCategory(), new HashSet<ProductItem>(Arrays.asList(new ProductItem(product.getTitle(), product.getPrice(), product.getCategory(), quantity))), quantity));

            }
        }
        cost += (product.getPrice() * quantity);

    }

    public void applyCoupon(Coupon coupon) {
        if (afterDiscountsCost == 0) {
            // in this case campaign is not requested yet. Before call this method can validate afterDiscountsCost is equals to zero
            afterDiscountsCost = cost;
        }
        if (afterDiscountsCost > coupon.getDiscountMinPrice()) {
            if (coupon.getType() == DiscountType.Rate) {
                couponDiscount = afterDiscountsCost * coupon.getDiscount() / 100;
                afterDiscountsCost -= couponDiscount;
            } else if (coupon.getType() == DiscountType.Rate) {
                couponDiscount = coupon.getDiscount();
                afterDiscountsCost -= couponDiscount;
            }
        }
    }

    public void applyCampaign(Campaign... campaigns) {
        double gtTotalAmount = 0;
        for (Campaign campaign : campaigns) {
            Set<CategorizedProducts> filtered = products.stream().filter(categorizedProducts -> {
                if (categorizedProducts.getCategory().getId() == campaign.getCategory().getId()) {
                    return true;
                } else
                    return false;
            }).collect(Collectors.toSet());
            if (filtered.size() > 0 && filtered.iterator().next().getProductsCount() > campaign.getQuantity()) {
                double totalPriceBeforeAmount = 0, totalAmount = 0;
                for (ProductItem product : filtered.iterator().next().getProducts()) {
                    totalPriceBeforeAmount += (product.getPrice() * product.getQuantity());
                }
                if (campaign.getType() == DiscountType.Rate) {
                    totalAmount = totalPriceBeforeAmount * campaign.getDiscount() / 100;
                } else if (campaign.getType() == DiscountType.Amount) {
                    totalAmount = campaign.getDiscount();
                }
                gtTotalAmount = totalAmount > gtTotalAmount ? totalAmount : gtTotalAmount;
            }
        }
        campaignDiscount = gtTotalAmount;
        afterDiscountsCost = cost - campaignDiscount;
    }

    public double getTotalAmountAfterDiscounts() {
        return afterDiscountsCost;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void print() {
        for (CategorizedProducts categorizedProducts : products) {
            if (categorizedProducts.getCategory().isParent() == false) {
                System.out.println(Utils.getCategoryNameString(categorizedProducts.getCategory().getTitle()));
                System.out.println(Utils.getProductsHeaderString());
                for (ProductItem productItem : categorizedProducts.getProducts()) {
                    System.out.println(Utils.getProductString(productItem.getTitle(), productItem.getQuantity(), productItem.getPrice()));
                }
            }
        }
        System.out.println(Utils.getTotalPriceString(cost));
        System.out.println(Utils.getTotalDiscountString((getCampaignDiscount()) + getCouponDiscount()));
        System.out.println(Utils.getTotalAmountDeliveryCostString(deliveryCost, afterDiscountsCost + deliveryCost));
    }

    public ShoppingCart(Set<CategorizedProducts> products, double cost, double afterDiscountsCost, double couponDiscount, double campaignDiscount, double deliveryCost) {
        super();
        this.products = products;
        this.cost = cost;
        this.afterDiscountsCost = afterDiscountsCost;
        this.couponDiscount = couponDiscount;
        this.campaignDiscount = campaignDiscount;
        this.deliveryCost = deliveryCost;
    }

    public ShoppingCart() {
        super();
    }

}
