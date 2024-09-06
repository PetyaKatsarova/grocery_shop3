package com.example.grocery.shop3.model;



public interface DiscountStrategy {
    /**
     * all classes have default discount values
     * */
    double getDiscount(Item item);
}


