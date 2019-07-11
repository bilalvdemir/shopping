package com.example.shopping.shopping.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    private String          id;
    @NotNull
    private Category     category;
    @Min(1)
    private double       discount;
    @Min(1)
    private int          quantity;
    @NotNull
    private DiscountType type;

    public Campaign(Category category, double discount, int quantity, DiscountType type) {
        super();
        this.category = category;
        this.discount = discount;
        this.quantity = quantity;
        this.type = type;
    }

}
