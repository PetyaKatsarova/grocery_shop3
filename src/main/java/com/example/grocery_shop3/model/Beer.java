package com.example.grocery_shop3.model;

import org.jetbrains.annotations.NotNull;

public class Beer extends Item {
    public enum BeerType {BELGIUM, DUTCH, GERMAN}

    /**
     * unit is currently bottle or pack of 6 bottles
     */
    private final BeerType  beerType;
    /**
     * discounts are per pack of 6 bottles or 6 individual bottles
     */
    private static double   BELGIUM_DISCOUNT = 3.00;
    private static double   DUTCH_DISCOUNT = 2.00;
    private static double   GERMAN_DISCOUNT = 4.00;
    private static int      BOTTLES_PER_PACK = 6;

    public Beer() {
        super("beer", 0.5, "bottle", 1);
        this.beerType = BeerType.DUTCH;
        this.unit = "bottle";
    }

    public Beer(String name, double price, BeerType type, String unit) {
        super(name, price, "bottle", 1);
        this.beerType = type;
        setUnit(unit);
    }

    public Beer(String name, double price, BeerType type, int quantity, String unit) {
        super(name, price, unit, quantity);
        this.beerType = type;
        setUnit(unit);
    }

    @Override
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - getDiscount();
    }

    @Override
    public double getDiscount() {
        int packs = 0;
        if (this.unit.equals("bottle")) {
            if (this.quantity >= BOTTLES_PER_PACK) packs = (int) Math.floor((double) quantity / BOTTLES_PER_PACK);
            else  return 0.0;
        } else if (this.unit.equals("pack"))  packs = this.quantity;

        switch (this.beerType) {
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
    public void setUnit(@NotNull String unit) {
        if (!unit.equals("pack") && !unit.equals("bottle")) {
            throw new IllegalArgumentException("Beer unit can be only a pack or a bottle and not an empty string.");
        }
        this.unit = unit;
    }

    public BeerType getType() {
        return beerType;
    }
}
