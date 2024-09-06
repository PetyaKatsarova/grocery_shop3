package com.example.grocery.shop3.model;

public class DiscountStrategyBread implements DiscountStrategy {

    private int    daysSmallDiscount;
    private int    daysBigDiscount;
    private int    smallNumDeal;
    private int    bigNumDeal;

    public DiscountStrategyBread() {
        this.daysSmallDiscount = 3;
        this.daysBigDiscount = 6;
        this.smallNumDeal = 2;
        this.bigNumDeal = 3;
    }

    public DiscountStrategyBread(int daysSmallDiscount, int daysBigDiscount, int smallNumDeal, int bigNumDeal) {
        this.daysSmallDiscount = daysSmallDiscount;
        this.daysBigDiscount = daysBigDiscount;
        this.smallNumDeal = smallNumDeal;
        this.bigNumDeal = bigNumDeal;
    }

    @Override
    public String toString() {
        return "\nBread( " + this.daysSmallDiscount + " - " + (this.daysBigDiscount - 1) +
                " days old): buy 1 get " + this.smallNumDeal +
                "; (" + this.daysBigDiscount + " days old): buy 1 get " + this.bigNumDeal + ";";
    }

    @Override
    public double getDiscount(Item item) {
        if (!(item instanceof Bread bread)) {
            throw new IllegalArgumentException("Item must be Bread");
        }
        if (bread.getAge() >= daysSmallDiscount && bread.getAge() < daysBigDiscount) {
            return bread.getPrice() * Math.floor((double) bread.getQuantity() / smallNumDeal);
        } else if (bread.getAge() == daysBigDiscount) {
            return bread.getPrice() * Math.floor((double) bread.getQuantity() / bigNumDeal);
        }
        return 0.0;
    }

    public void setDaysSmallDiscount(int daysSmallDiscount) {
        this.daysSmallDiscount = daysSmallDiscount;
    }

    public void setDaysBigDiscount(int daysBigDiscount) {
        this.daysBigDiscount = daysBigDiscount;
    }

    public void setSmallNumDeal(int smallNumDeal) {
        this.smallNumDeal = smallNumDeal;
    }

    public void setBigNumDeal(int bigNumDeal) {
        this.bigNumDeal = bigNumDeal;
    }
}