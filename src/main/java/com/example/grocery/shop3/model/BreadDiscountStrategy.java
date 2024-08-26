package com.example.grocery.shop3.model;

public class BreadDiscountStrategy extends DiscountStrategy{
    /**
     * If bread is at least 3 days old get discount: buy 1 get 2; if 6 days old: buy 1 get 3
     * */
    private static int DAYS_SMALL_DISCOUNT  = 3;
    private static int DAYS_BIG_DISCOUNT    = 6;
    private static int SMALL_NUM_DEAL       = 2;
    private static int BIG_NUM_DEAL         = 3;

    private static String   DISCOUNT_CONDITIONS = "\nBread(3 - 5 days old): buy 1 get 2; (6 days old): buy 1 get 3; ";
    @Override
    public double getDiscount(Item item) {
        if (!(item instanceof Bread bread)) {
            throw new IllegalArgumentException("Item must be Bread");
        }
        if (bread.getAge() >= DAYS_SMALL_DISCOUNT && bread.getAge() < DAYS_BIG_DISCOUNT) {
            return bread.getPrice() * (Math.floor((double) bread.getQuantity() / (SMALL_NUM_DEAL)));
        } else if (bread.getAge() == DAYS_BIG_DISCOUNT) {
            return bread.getPrice() * (Math.floor((double) bread.getQuantity() / (BIG_NUM_DEAL)));
        }
        return 0.0;
    }

    @Override
    public String getDiscountRules() {
        return DISCOUNT_CONDITIONS;
    }

    @Override
    public void changeDiscountValues(Item item) {

    }
}
