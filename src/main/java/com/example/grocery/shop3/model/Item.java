package com.example.grocery.shop3.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "name"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Beer.class, name = "beer"),
        @JsonSubTypes.Type(value = Bread.class, name = "bread"),
        @JsonSubTypes.Type(value = Vegetable.class, name = "vegetable")
})
public abstract class Item {
    protected String            name;
    protected double            price;
    protected int               quantity;
    protected String            unit;
    protected DiscountStrategy  discountStrategy;

    public Item(String name, double price, String unit, DiscountStrategy discountStrategy) {
        setName(name);
        setPrice(price);
        setUnit(unit);
        quantity = 1;
       this.discountStrategy = discountStrategy;
    }

    public Item(String name, double price, String unit, int quantity, DiscountStrategy discountStrategy) {
        setName(name);
        setPrice(price);
        setUnit(unit);
        setQuantity(quantity);
        this.discountStrategy = discountStrategy;
    }

    public abstract double getTotal();
    public abstract double getTotalAfterDiscount();
    public abstract String toString();

    public void setName(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name can't be empty string");
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0.0) throw new IllegalArgumentException("Price cant be 0 or negative");
        this.price = price;
    }

    // protected because for now used only in class and subclass
    protected void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity cant be 0 or negative");
        this.quantity = quantity;
    }

    protected void setUnit(String unit) {
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