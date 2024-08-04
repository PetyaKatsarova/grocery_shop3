package com.example.grocery.shop3.model;

import org.jetbrains.annotations.NotNull;

public class Beer extends Item {
    public enum BeerType {BELGIUM, DUTCH, GERMAN}

    /**
     * unit is currently bottle or pack of 6 bottles
     */
    private final BeerType  beerType;

    public Beer() {
        super("beer", 0.5, "bottle", 1, new BeerDiscountStrategy());
        this.beerType = BeerType.DUTCH;
        this.unit = "bottle";
    }

    public Beer(String name, double price, BeerType type, String unit) {
        super(name, price, "bottle", 1, new BeerDiscountStrategy());
        this.beerType = type;
        setUnit(unit);
    }

    public Beer(String name, double price, BeerType type, int quantity, String unit) {
        super(name, price, unit, quantity, new BeerDiscountStrategy());
        this.beerType = type;
        setUnit(unit);
    }

    public Beer(String name, double price, BeerType type, int quantity, String unit, BeerDiscountStrategy beerDiscountStrategy) {
        super(name, price, unit, quantity, beerDiscountStrategy);
        this.beerType = type;
        setUnit(unit);
    }

    @Override
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - discountStrategy.getDiscount(this);
    }

    @Override
    public String toString() {
        return String.format("%d %s x %s %s €%.2f\n",
                this.getQuantity(), unitCorrectSpellingHelper(), this.getType().toString().toLowerCase(), this.getName(), this.getTotal()) +
                String.format("   Discount: €%.2f\n", this.discountStrategy.getDiscount(this));
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
