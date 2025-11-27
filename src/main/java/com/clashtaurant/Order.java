package com.clashtaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final double TAX_RATE = 1.13;
    private List<FoodItem> orderContents = new ArrayList<>();

    public boolean getCheckoutPossibility() {
        return orderContents.isEmpty();
    }

    public List<FoodItem> getOrderContents() {
        return orderContents;
    }

    public double calculateFinalPrice() {
        double totalPrice = 0;
        for (FoodItem item :
                orderContents) {
             totalPrice += item.getPrice();
        }
        return Math.round((totalPrice * TAX_RATE) * 100.0) / 100.0;
    }
}
