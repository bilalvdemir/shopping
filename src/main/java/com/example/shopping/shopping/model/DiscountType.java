package com.example.shopping.shopping.model;

public enum DiscountType {

    Rate("Rate"), Amount("Amount");

    private DiscountType(String type) {
        this.type = type;
    }

    private final String type;

    @Override
    public String toString() {
        return type;

    }

    public static DiscountType fromString(String text) {
        if (text != null) {
            for (DiscountType type : DiscountType.values()) {
                return type;
            }
        }
        return null;
    }

}
