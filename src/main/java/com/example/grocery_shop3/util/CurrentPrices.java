package com.example.grocery_shop3.util;

public class CurrentPrices {
    /**
     * I assume based on the README info that all types of beers, breads and veggies have the same price
     * vegetables have the same price
     * */

    private double currentBeerPricePerBottle = 4.2;
    private double currentBeerPricePerPack = 24.0;
    private double currentBreadPrice = 3.42;
    private double currentVegetablesPricePerGr = 0.09;

    public CurrentPrices(int currentBeerPricePerBottle, int currentBreadPrice, int currentVegetablesPricePerGr) {
        this.currentBeerPricePerBottle = currentBeerPricePerBottle;
        this.currentBreadPrice = currentBreadPrice;
        this.currentVegetablesPricePerGr = currentVegetablesPricePerGr;
    }

    public CurrentPrices() {}

    public double getCurrentBeerPricePerBottle() {
        return currentBeerPricePerBottle;
    }

    public double getCurrentBeerPricePerPack() {
        return currentBeerPricePerPack;
    }

    public double getCurrentBreadPrice() {
        return currentBreadPrice;
    }

    public double getCurrentVegetablesPricePerGr() {
        return currentVegetablesPricePerGr;
    }

    public void setCurrentBeerPricePerBottle(double currentBeerPricePerBottle) {
        this.currentBeerPricePerBottle = currentBeerPricePerBottle;
    }

    public void setCurrentBeerPricePerPack(double currentBeerPricePerPack) {
        this.currentBeerPricePerPack = currentBeerPricePerPack;
    }

    public void setCurrentBreadPrice(double currentBreadPrice) {
        this.currentBreadPrice = currentBreadPrice;
    }

    public void setCurrentVegetablesPricePerGr(double currentVegetablesPricePerGr) {
        this.currentVegetablesPricePerGr = currentVegetablesPricePerGr;
    }
}
