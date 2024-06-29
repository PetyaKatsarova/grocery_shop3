package com.example.grocery_shop3.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "name"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Beer.class, name = "beer"),
        @JsonSubTypes.Type(value = Bread.class, name = "bread"),
        @JsonSubTypes.Type(value = Vegetable.class, name = "vegetable")
})
public abstract class Item {
    protected String    name;
    protected double    price;
    protected int       quantity;
    protected String    unit;

    public Item(String name, double price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        quantity = 1;
    }

    public Item(String name, double price, String unit, int quantity) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public abstract double getTotal();
    public abstract double getDiscount();
    public abstract double getTotalAfterDiscount();
}