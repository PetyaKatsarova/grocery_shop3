package com.example.grocery.shop3.model;

public class BeerDiscountStrategy extends DiscountStrategy {

    /**
     * discounts are per pack of 6 bottles or 6 individual bottles
     */
    private static double   BELGIUM_DISCOUNT = 3.00;
    private static double   DUTCH_DISCOUNT = 2.00;
    private static double   GERMAN_DISCOUNT = 4.00;
    private static int      BOTTLES_PER_PACK = 6;
    private static String   BEER_DISCOUNT =  "\nBeer(6 bottles): Belgium -€3.00; Dutch - €2.00; German -€4.00; ";

    public BeerDiscountStrategy() {

    }

    @Override
    public double getDiscount(Item item) {
        int packs = 0;
        if (!(item instanceof Beer beer)) {
            throw new IllegalArgumentException("Item must be Beer");
        }
        if (beer.unit.equals("bottle")) {
            if (beer.quantity >= BOTTLES_PER_PACK) packs = (int) Math.floor((double) beer.quantity / BOTTLES_PER_PACK);
            else  return 0.0;
        } else if (beer.unit.equals("pack"))  packs = beer.quantity;

        switch (beer.getType()) {
            case BELGIUM -> {
                return BELGIUM_DISCOUNT * packs;
            }
            case DUTCH -> {
                return DUTCH_DISCOUNT * packs;
            }
            case GERMAN -> {
                return GERMAN_DISCOUNT * packs;
            }
        }
        return 0.0;
    }

    @Override
    public String getDiscountRules() {
        return BEER_DISCOUNT;
    }

    @Override
    public void changeDiscountValues(Item item) {
//        TODO
    }
}
