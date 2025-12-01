package com.clashtaurant;

public class TestClass {
    public static void main(String[] args) {
        MenuRegistry.init();

        Order order = new Order();
        new GUI(order);
    }
}
