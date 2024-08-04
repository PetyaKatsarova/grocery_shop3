package com.example.grocery.shop3.model;

public class Bread extends Item {
    private int             age;

    /**
     * if age is bigger than 6 days don't sell
     * */
    private static final int AGE_DONT_SELL = 7;

    public Bread() {
        super("bread", 1.00, "loaf", new BreadDiscountStrategy());
        this.age = 1;
    }

    public Bread(String name, double price, int age) {
        super(name, price, "loaf", new BreadDiscountStrategy());
        setAge(age);
        this.quantity = 1;
    }

    public Bread(String name, double price, int age, int quantity) {
        super(name, price, "loaf", new BreadDiscountStrategy());
        setAge(age);
        this.quantity = quantity;
    }
    public Bread(String name, double price, int age, int quantity, String unit) {
        super(name, price, unit, new BreadDiscountStrategy());
        setAge(age);
        this.quantity = quantity;
    }

    public Bread(String name, double price, int age, int quantity, String unit, BreadDiscountStrategy breadDiscountStrategy) {
        super(name, price, unit, breadDiscountStrategy);
        setAge(age);
        this.quantity = quantity;
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
        return String.format("%d x %s €%.2f\n",
                this.getQuantity(), this.getName(), this.getTotal()) +
                String.format("   Discount: €%.2f\n", this.discountStrategy.getDiscount(this));
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age >= AGE_DONT_SELL) {
            throw new IllegalArgumentException("Bread is too old to sell, over/equal "+AGE_DONT_SELL+" days old");
        }
        this.age = age;
    }
}



