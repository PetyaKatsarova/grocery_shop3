package com.example.grocery_shop3.model;

public class Beer extends Item {
    public enum BeerType {
        BELGIUM, DUTCH, GERMAN
    }

    public enum TypeQuantity {
        PACK, BOTTLE
    }

    private BeerType type;

    public Beer(String name, double price, BeerType type, TypeQuantity typeQuantity) {
        super(name, price);
        this.type = type;
        this.typeQuantity = typeQuantity;
    }

    public Beer(String name, double price, BeerType type, int quantity) {
        super(name, price, quantity);
        this.type = type;
    }

    public BeerType getType() {
        return type;
    }


    /**
     *  Beers have only discounts if bought in packs containing 6
     * beers. The discount rules are fixed per pack:
     * ● € 3,00 for each Belgium beer pack
     * ● € 2,00 for each Dutch beer pack
     * ● € 4,00 for each German beer pack
     * Single bottles/cans of beer can always be added to the order,
     * but in that case there is no discount. Buying 6 separate
     * bottles of the same beer is the same as buying one pack of
     * the same beer
     * */
    @Override
    public double getTotalPrice() {
        double discount = 0.0;
        if (quantity >= 6) {
            switch (type) {
                case BELGIUM:
                    discount = 3.0;
                    break;
                case DUTCH:
                    discount = 2.0;
                    break;
                case GERMAN:
                    discount = 4.0;
                    break;
            }
            return price * quantity - discount;
        }
        return price * quantity;
    }
}
