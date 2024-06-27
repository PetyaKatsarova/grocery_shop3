package com.example.grocery_shop3.model;

public abstract class Item {
    protected String    name;
    protected double    price;
    protected int       quantity;
    protected String    unit;

    public Item(String name, double price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        quantity = 1;
    }

    public Item(String name, double price, String unit, int quantity) {
        this.name = name;
        this.price = price;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract double getTotal();
    public abstract double getDiscount();
    public abstract double getTotalAfterDiscount();
}