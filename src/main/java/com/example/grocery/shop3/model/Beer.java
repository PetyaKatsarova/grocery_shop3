package com.example.grocery.shop3.model;

import org.jetbrains.annotations.NotNull;

public class Beer extends Item {
    public enum BeerType {BELGIUM, DUTCH, GERMAN}
    private final BeerType beerType;

    public Beer() {
        super("beer", 0.5, "bottle", 1, new DiscountStrategyBeer());
        this.beerType = BeerType.DUTCH;
    }

    public Beer(String name, double price, BeerType type, String unit) {
        super(name, price, unit, new DiscountStrategyBeer());
        this.beerType = type;
    }

    public Beer(String name, double price, BeerType type, int quantity, String unit) {
        super(name, price, unit, quantity, new DiscountStrategyBeer());
        this.beerType = type;
    }

    public Beer(String name, double price, BeerType type, int quantity, String unit, DiscountStrategyBeer discountStrategyBeer) {
        super(name, price, unit, quantity, discountStrategyBeer);
        this.beerType = type;
    }

    @Override
    public String toString() {
        return String.format("%d %s x %s €%.2f\n",
                this.getQuantity(), unitCorrectSpellingHelper(), this.getName(), this.getTotal()) +
                String.format("   Discount: €%.2f\n", this.discountStrategy.getDiscount(this));
    }

    public BeerType getType() {
        return beerType;
    }

    @Override
    protected void setUnit(String unit) {
        if (!unit.equals("bottle") && !unit.equals("pack")) {
            throw new IllegalArgumentException("Beer can only be sold in bottles or packs.");
        }
        this.unit = unit;
    }

    private String unitCorrectSpellingHelper() {
        if (this.getUnit().equals("bottle")) {
            return "bottles";
        }
        if (this.getUnit().equals("pack")) {
            return "packs";
        }
        throw new IllegalArgumentException("Wrong unit: it can be only bottle or pack");
    }
}

