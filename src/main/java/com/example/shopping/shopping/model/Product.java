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
public class Product {

    @Id
    private String      id;
    @NotNull
    private String   title;
    @Min(1)
    private double   price;
    @NotNull
    private Category category;

    public Product(String title, double price, Category category) {
        super();
        this.title = title;
        this.price = price;
        this.category = category;
    }

}
