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
            return bread.getPrice() * Math.floor((double) bread.getQuantity() / smallNumDeal) * (smallNumDeal - 1); // last part is in case the num is changed
        } else if (bread.getAge() >= daysBigDiscount) {
            return bread.getPrice() * Math.floor((double) bread.getQuantity() / bigNumDeal) * (bigNumDeal - 1); // buy 1 get 3(get 2 free)
        }
        return 0.0;
    }

    /**
     * the setters are needed for spring to be able to update fields with json via http post req
     * */
    private void setDaysSmallDiscount(int daysSmallDiscount) {
        this.daysSmallDiscount = daysSmallDiscount;
    }

    private void setDaysBigDiscount(int daysBigDiscount) {
        this.daysBigDiscount = daysBigDiscount;
    }

    private void setSmallNumDeal(int smallNumDeal) {
        this.smallNumDeal = smallNumDeal;
    }

    private void setBigNumDeal(int bigNumDeal) {
        this.bigNumDeal = bigNumDeal;
    }
}
