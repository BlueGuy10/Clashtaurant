package com.clashtaurant;

public class TestClass {
    public static void main(String[] args) {
        // Initialize and register all menu items so they appear in the GUI
        MenuRegistry.init();

        // Create an empty order and launch the GUI
        Order order = new Order();
        new GUI(order);
    }
}
