package com.example.shopping.shopping.utils;

import java.text.MessageFormat;

public class Utils {

    public static final String CATEGORY_NAME         = "_________________________________\nProducts Category : {0}";
    public static final String PRODUCTS_HEADER       = "Product Name \t Quantity \t Unit Price";
    public static final String PROUCT_PRINT          = "{0}\t\t {1}\t\t {2}";
    public static final String TOTAL_PRICE           = "Total Price : {0}";
    public static final String TOTAL_DISCOUNT        = "Total Discount : {0}";
    public static final String TOTAL_AMOUNT_DELIVERY = "Delivery Cost : {0}\n_________________________________\nTotal Amount: {1}";

    public static String getCategoryNameString(String categoryName) {
        return MessageFormat.format(CATEGORY_NAME, categoryName);
    }

    public static String getProductsHeaderString() {
        return PRODUCTS_HEADER;
    }

    public static String getProductString(String productName, int quantity, Double unitPrice) {
        return MessageFormat.format(PROUCT_PRINT, productName, quantity, unitPrice);
    }

    public static String getTotalPriceString(double cost) {
        return MessageFormat.format(TOTAL_PRICE, cost);
    }

    public static String getTotalDiscountString(double cost) {
        return MessageFormat.format(TOTAL_DISCOUNT, cost);
    }

    public static String getTotalAmountDeliveryCostString(double delivery, double totalCost) {
        return MessageFormat.format(TOTAL_AMOUNT_DELIVERY, delivery, totalCost);
    }

}
