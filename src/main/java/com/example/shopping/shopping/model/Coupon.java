package com.example.shopping.shopping.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    private String          id;
    @Min(1)
    private double       discountMinPrice;
    @Min(1)
    private double       discount;
    @NotNull
    private DiscountType type;

    public Coupon(double discountMinPrice, double discount, DiscountType type) {
        super();
        this.discountMinPrice = discountMinPrice;
        this.discount = discount;
        this.type = type;
    }

}
