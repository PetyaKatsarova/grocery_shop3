package com.example.grocery.shop3.model;

public class DiscountStrategyVegetable implements DiscountStrategy {

    private double smallDiscount;
    private double mediumDiscount;
    private double bigDiscount;

    public DiscountStrategyVegetable() {
        this.smallDiscount = 0.05;
        this.mediumDiscount = 0.07;
        this.bigDiscount = 0.10;
    }

    public DiscountStrategyVegetable(double smallDiscount, double mediumDiscount, double bigDiscount) {
        this.smallDiscount = smallDiscount;
        this.mediumDiscount = mediumDiscount;
        this.bigDiscount = bigDiscount;
    }

    @Override
    public double getDiscount(Item item) {
        if (!(item instanceof Vegetable vegetable)) {
            throw new IllegalArgumentException("Item must be Vegetable");
        }

        double commonCalculation = vegetable.price * (vegetable.quantity / vegetable.getWeight_for_price());
        if (vegetable.quantity == 0) {
            return 0.0;
        }
        if (vegetable.quantity <= 100) {
            return smallDiscount * commonCalculation;
        } else if (vegetable.quantity <= 500) {
            return mediumDiscount * commonCalculation;
        } else {
            return bigDiscount * commonCalculation;
        }
    }

    @Override
    public String toString() {
        return "\nVegetables(1 - 100g): " + (this.smallDiscount * 100) + "%; (101 - 500g): " +
                Math.round((this.mediumDiscount * 100) * 100.0) / 100.0 +
                "%; Over 500g: " + (this.bigDiscount * 100) + "%;\n";
    }

    public void setSmallDiscount(double smallDiscount) {
        this.smallDiscount = smallDiscount;
    }

    public void setMediumDiscount(double mediumDiscount) {
        this.mediumDiscount = mediumDiscount;
    }

    public void setBigDiscount(double bigDiscount) {
        this.bigDiscount = bigDiscount;
    }
}
