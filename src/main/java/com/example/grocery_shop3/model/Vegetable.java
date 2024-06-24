package com.example.grocery_shop3.model;

public class Vegetable extends Item {
    private double weight;

    public Vegetable(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double getTotalPrice() {
        double discount = 0.0;
        if (weight <= 100) {
            discount = 0.05;
        } else if (weight <= 500) {
            discount = 0.07;
        } else {
            discount = 0.10;
        }
        return price * weight * (1 - discount);
    }
}

