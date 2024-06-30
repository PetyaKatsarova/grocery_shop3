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
        setName(name);
        setPrice(price);
        setUnit(unit);
        quantity = 1;
    }

    public Item(String name, double price, String unit, int quantity) {
        setName(name);
        setPrice(price);
        setUnit(unit);
        setQuantity(quantity);
    }

    public abstract double getTotal();
    public abstract double getDiscount();
    public abstract double getTotalAfterDiscount();

    public void setName(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name can't be empty string");
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0.0) throw new IllegalArgumentException("Price cant be 0 or negative");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity cant be 0 or negative");
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        if (unit.isEmpty()) throw new IllegalArgumentException("Unit can't be empty string");
        this.unit = unit;
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
}