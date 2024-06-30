package com.example.grocery_shop3.model;

public class Vegetable extends Item {
    /**
     * example: price: 1 euro per 100g
     * */
    private double          weight_for_price;
    /**
     * discount is in %: example: 5% = 0.05
     * */
    private static double    SMALL_DISCOUNT = 0.05;
    private static double    MEDIUM_DISCOUNT = 0.07;
    private static double    BIG_DISCOUNT = 0.1;

    /**
     * by default vegetable price is per 100g;
     * quantity is the weight
     * */
    public Vegetable() {
        super("vegetable", 1.00, "g", 100);
        this.weight_for_price = 100.00;
    }

    public Vegetable(String name, double price, double weight, double weight_for_price) {
        super(name, price, "g", 100);
        setWeight_for_price(weight_for_price);
    }

    public Vegetable(String name, double price, int weight, String unit, double weight_for_price) {
        super(name, price, unit, weight);
        this.unit = unit;
        setWeight_for_price(weight_for_price);
    }

    @Override
    public double getTotal() {
        return price * (quantity / weight_for_price);
    }

    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - getDiscount();
    }

    @Override
    public double getDiscount() {
        double common_calculation = price * (quantity / weight_for_price);
        if (quantity == 0)
            return 0.0;
        if (quantity <= 100) {
            return SMALL_DISCOUNT * common_calculation;
        } else if (quantity <= 500) {
            return MEDIUM_DISCOUNT * common_calculation;
        } else {
            return BIG_DISCOUNT * common_calculation;
        }
    }

    public double getWeight_for_price() {
        return weight_for_price;
    }

    public void setWeight_for_price(double weight_for_price) {
        if (weight_for_price <= 0) throw new IllegalArgumentException("the weight for price should be higher than 0");
        this.weight_for_price = weight_for_price;
    }
}
