package com.example.grocery.shop3.model;



public abstract class DiscountStrategy {
    /**
     * all classes have default discount values
     * */
    Item item = null;
    abstract double getDiscount(Item item);
    abstract String getDiscountRules();
    abstract void changeDiscountValues(Item item);
}


