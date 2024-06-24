package com.example.grocery_shop3.model;

public class Bread extends Item {
    private int age;

    public Bread(String name, double price, int age) {
        super(name, price);
        this.age = age;
    }
    public Bread(String name, double price, int age, int quantity) {
        super(name, price, quantity);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

//    TODO: if i get 2 days old bread, or 4,5 days each? CONSIDER: IF client gets 1 1day old bread, 1 3 days old and 2 bread 6 days old....
//    ● Breads always have discounts like “buy 1 take 2”, or “buy 1
//    take 3”. If the bread is one day old or newer, it has no
//    discounts. If it's 3 days old it has the “buy 1 take 2” discount,
//    and if it's 6 days old, it's “pay 1 take 3”. Breads older than 6
//    days cannot be added to orders.

    @Override
    public double getTotalPrice() {
        return (price * quantity) - getDiscount();
    }

    /**
     * Returns discount applied according to conditions: if the bread is:
     * one day old or newer:no discount.
     * 3 days old: “buy 1 take 2” discount.
     * 6 days old: “pay 1 take 3”.
     * older than 6 days cannot be added to orders.
     * */
    @Override
    public double getDiscount() {
        if (age <= 1) {
            return price;
        } else if (age == 3) {
            if (quantity % 2 == 0) {
                return price * (quantity / 2.0);
            } else {
                int notDiscountedQuantity = quantity % 2;
                return (price * (quantity - notDiscountedQuantity)) / 2.0;
            }
        } else if (age == 6) {
            if (quantity % 3 == 0) {
                return price * (quantity / 3.0);
            } else {
                int notDiscountedQuantity = quantity % 3;
                return (price * (quantity - notDiscountedQuantity)) / 3.0;
            }
        } else {
            throw new IllegalArgumentException("Bread older than 6 days cannot be added.");
        }
    }
}



