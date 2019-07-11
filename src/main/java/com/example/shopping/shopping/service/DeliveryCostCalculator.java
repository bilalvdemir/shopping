package com.example.shopping.shopping.service;

import java.util.stream.Collectors;

import com.example.shopping.shopping.model.ShoppingCart;

import lombok.Data;

@Data
public class DeliveryCostCalculator {

    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost = 2.99;

    public double calculateFor(ShoppingCart cart) {
        double numberOfDeliveries = cart.getProducts().stream().filter(item -> !item.getCategory().isParent()).count();
        double numberOfProducts = cart.getProducts().stream().filter(item -> !item.getCategory().isParent()).map(item -> item.getProducts().size()).collect(Collectors.summingDouble(Integer::intValue));
        return (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
    }

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct) {
        super();
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
    }
}
