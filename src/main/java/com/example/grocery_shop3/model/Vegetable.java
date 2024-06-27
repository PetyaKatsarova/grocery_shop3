package com.example.grocery_shop3.model;

public class Vegetable extends Item {
    private double          weight;
    private String          unit;
    private final double    SMALL_DISCOUNT = 0.05;
    private final double    MEDIUM_DISCOUNT = 0.07;
    private final double    BIG_DISCOUNT = 0.1;

    public Vegetable(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
        this.unit = "gram";
    }

    public Vegetable(String name, double price, double weight, int quantity, String unit) {
        super(name, price, quantity);
        this.weight = weight;
        this.unit = unit;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double getTotal() {
        return price * weight;
    }

    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - getDiscount();
    }

    /**
     * ● Vegetables have a % based discount depending on the
     * weight. If you buy between 0g and 100g (always in the same
     * order), you have 5% of discount (applied to all vegetable
     * items), 7% between 100g and 500g, and 10% of fixed discount
     * if all vegetables weight more than 500g.
     * */
//    I assume: from 1 to 100 inclusive, from 101 to 500 g inclusive, from 501 g
//    I assume: price is per gram weight
    @Override
    public double getDiscount() {
        double discount = 0.0;
        if (weight == 0)
            return 0.0;
        if (weight <= 100) {
            return SMALL_DISCOUNT * price * weight;
        } else if (weight <= 500) {
            return MEDIUM_DISCOUNT * price * weight;
        } else {
            return BIG_DISCOUNT * price * weight;
        }
    }

    @Override
    public double getTotalPriceAfter() {
        return 0;
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}

