package com.clashtaurant;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodItem implements Cloneable {
    private final String name;
    private final String description;
    private final String imagePath;
    private Size size = Size.M;
    private final double sPrice;
    private final double mPrice;
    private final double lPrice;
    private static List<FoodItem> mains = new ArrayList<FoodItem>();
    private static List<FoodItem> desserts = new ArrayList<FoodItem>();
    private static List<FoodItem> drinks = new ArrayList<FoodItem>();

    public FoodItem(String name, String description, String imagePath, double sPrice, double mPrice, double lPrice, FoodCategory category) {
        this.description = description;
        this.name = name;
        this.imagePath = imagePath;
        this.sPrice = sPrice;
        this.mPrice = mPrice;
        this.lPrice = lPrice;
        switch (category) {
            case FoodCategory.MAIN ->  mains.add(this);
            case FoodCategory.DESSERT ->  desserts.add(this);
            case FoodCategory.DRINK ->  drinks.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return switch (size) {
            case S -> sPrice;
            case M -> mPrice;
            case L -> lPrice;
        };
    }

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static List<FoodItem> getMains() {
        return mains;
    }

    public static List<FoodItem> getDesserts() {
        return desserts;
    }

    public static List<FoodItem> getDrinks() {
        return drinks;
    }

    public double getSPrice() {
        return sPrice;
    }

    public double getMPrice() {
        return mPrice;
    }

    public double getLPrice() {
        return lPrice;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
