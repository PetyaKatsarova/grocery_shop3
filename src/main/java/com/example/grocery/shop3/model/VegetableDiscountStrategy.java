package com.example.grocery.shop3.model;

public class VegetableDiscountStrategy extends DiscountStrategy {
    private static String   VEGETABLE_DISCOUNT = "\nVegetables(1 - 100g): 5%; (101 - 500g): 7%;Over 500g: 10% \n";
    /**
     * discount is in %: example: 5% = 0.05
     * */
    private static double    SMALL_DISCOUNT = 0.05;
    private static double    MEDIUM_DISCOUNT = 0.07;
    private static double    BIG_DISCOUNT = 0.1;

    public VegetableDiscountStrategy() {}
    @Override
    public double getDiscount(Item item) {
        if (!(item instanceof Vegetable vegetable)) {
            throw new IllegalArgumentException("Item must be Vegetable");
        }
        double common_calculation = vegetable.price * (vegetable.quantity / vegetable.getWeight_for_price());
        if (vegetable.quantity == 0)
            return 0.0;
        if (vegetable.quantity <= 100) {
            return SMALL_DISCOUNT * common_calculation;
        } else if (vegetable.quantity <= 500) {
            return MEDIUM_DISCOUNT * common_calculation;
        } else {
            return BIG_DISCOUNT * common_calculation;
        }
    }


    @Override
    public String getDiscountRules() {
        return VEGETABLE_DISCOUNT;
    }

    @Override
    public void changeDiscountValues(Item item) {
        // TODO
    }
}
