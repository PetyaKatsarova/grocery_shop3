package com.example.grocery.shop3.model;

public class DiscountStrategyBeer implements DiscountStrategy {

    /**
     * discounts are per bottlesPerPack default is 6
     */
    private double  belgiumDiscount;
    private double  dutchDiscount;
    private double  germanDiscount;
    private int     bottlesPerPack;

    public DiscountStrategyBeer() {
        this.belgiumDiscount = 3.00;
        this.dutchDiscount = 2.00;
        this.germanDiscount = 4.00;
        this.bottlesPerPack = 6;
    }

    public DiscountStrategyBeer(double belgiumDiscount, double dutchDiscount, double germanDiscount, int bottlesPerPack, String discountConditions) {
        this.belgiumDiscount = belgiumDiscount;
        this.dutchDiscount = dutchDiscount;
        this.germanDiscount = germanDiscount;
        this.bottlesPerPack = bottlesPerPack;
    }

    @Override
    public String toString() {
        return "\nBeer discount: (" + bottlesPerPack + " bottles): Belgium -€" + belgiumDiscount +
                "; Dutch -€" + dutchDiscount + "; German -€" + germanDiscount + ";";
    }

    @Override
    public double getDiscount(Item item) {
        int packs = 0;
        if (!(item instanceof Beer beer)) {
            throw new IllegalArgumentException("Item must be Beer");
        }
        if (beer.unit.equals("bottle")) {
            if (beer.quantity >= bottlesPerPack) packs = (int) Math.floor((double) beer.quantity / bottlesPerPack);
            else return 0.0;
        } else if (beer.unit.equals("pack")) packs = beer.quantity;

        switch (beer.getType()) {
            case BELGIUM -> {
                return belgiumDiscount * packs;
            }
            case DUTCH -> {
                return dutchDiscount * packs;
            }
            case GERMAN -> {
                return germanDiscount * packs;
            }
        }
        return 0.0;
    }

    /**
     * setters are needed for Spring to automatically deserialize the JSON into the DiscountStrategyBeer object and set the new, not default values using the setters.
     * */
    private void setBelgiumDiscount(double belgiumDiscount) {
        this.belgiumDiscount = belgiumDiscount;
    }

    private void setDutchDiscount(double dutchDiscount) {
        this.dutchDiscount = dutchDiscount;
    }

    private void setGermanDiscount(double germanDiscount) {
        this.germanDiscount = germanDiscount;
    }

    private void setBottlesPerPack(int bottlesPerPack) {
        this.bottlesPerPack = bottlesPerPack;
    }
}
