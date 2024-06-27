package com.example.grocery_shop3.model;

public class Beer extends Item {
    public enum BeerType { BELGIUM, DUTCH, GERMAN }
    public enum PackagingType { PACK, BOTTLE }
    private final BeerType      beerType;
    private final PackagingType packagingType;

    /**
     * discounts are per pack of 6 bottles or 6 individual bottles
     * */
     private final double   BelgiumDiscount = 3.00;
     private final double   DutchDiscount   = 2.00;
     private final double   GermanDiscount  = 4.00;
     private final int      BottlesPerPack = 6;

    public Beer(String name, double price, BeerType type, PackagingType packagingType) {
        super(name, price);
        this.beerType = type;
        this.packagingType = packagingType;
    }

    public Beer(String name, double price, BeerType type, PackagingType packagingType, int quantity) {
        super(name, price, quantity);
        this.beerType = type;
        this.packagingType = packagingType;
    }

    public BeerType getType() {
        return beerType;
    }

    public PackagingType getPackagingType() {
        return packagingType;
    }

    @Override
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public double getTotalAfterDiscount() {
        return  getTotal() - getDiscount();
    }


    /**
     *  Beers have only discounts if bought in packs containing 6
     * beers. The discount rules are fixed per pack:
     * Single bottles/cans of beer can always be added to the order,
     * but in that case there is no discount. Buying 6 separate
     * bottles of the same beer is the same as buying one pack of
     * the same beer
     * */

    public double getDiscount(){
        int packs = this.quantity;
        if (this.packagingType == PackagingType.BOTTLE) {
            if (quantity > 5) {
                packs = quantity / BottlesPerPack;
            } else {
                return 0.0;
            }
        }
        switch (this.beerType) {
            case BELGIUM -> {
                return (price * packs) - (BelgiumDiscount * packs);
            }
            case DUTCH -> {
                return (price * packs) - (DutchDiscount * packs);
            }
            case GERMAN -> {
                return (price * packs) - (GermanDiscount * packs);
            }
        }
        return  0.0;
    }
}
