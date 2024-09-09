package com.example.grocery.shop3.model;

public class Vegetable extends Item {
    /**
     * example: price: 1 euro per 100g
     * */
    private double  weight_for_price;

    /**
     * by default vegetable price is per 100g;
     * quantity is the weight
     * */
    public Vegetable() {
        super("vegetable", 1.00, "g", 100, new DiscountStrategyVegetable());
        this.weight_for_price = 100.00;
    }

    public Vegetable(String name, double price, double weight, double weight_for_price) {
        super(name, price, "g", 100, new DiscountStrategyVegetable());
        setWeight_for_price(weight_for_price);
    }

    public Vegetable(String name, double price, int weight, String unit, double weight_for_price) {
        super(name, price, unit, weight, new DiscountStrategyVegetable());
        this.unit = unit;
        setWeight_for_price(weight_for_price);
    }

    @Override
    public double getTotal() {
        return price * (quantity / weight_for_price);
    }

    @Override
    public String toString() {
        return String.format("%d %s x %s €%.2f\n",
                this.getQuantity(), this.getUnit(), this.getName(), this.getTotal()) +
                String.format("   Discount: €%.2f\n", this.discountStrategy.getDiscount(this));
    }

    public double getWeight_for_price() {
        return weight_for_price;
    }

    public void setWeight_for_price(double weight_for_price) {
        if (weight_for_price <= 0) throw new IllegalArgumentException("the weight for price should be higher than 0");
        this.weight_for_price = weight_for_price;
    }
}
