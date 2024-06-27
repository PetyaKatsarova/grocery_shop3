package com.example.grocery_shop3.model;

public abstract class Item {
    protected String    name;
    protected double    price;
    protected int       quantity;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        quantity = 1;
    }

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public abstract double getTotalPrice();

    public abstract double getTotalPriceAfter();
    public abstract double getDiscount();
}