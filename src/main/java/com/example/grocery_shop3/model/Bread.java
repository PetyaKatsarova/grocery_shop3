package com.example.grocery_shop3.model;

public class Bread extends Item {
    private int             age;
    /**
    * If bread is at least 3 days old get discount: buy 1 get 2; if 6 days old: buy 1 get 3
    * */
    private static final int DAYS_SMALL_DISCOUNT = 3;
    private static final int DAYS_BIG_DISCOUNT = 6;
    private static final int SMALL_NUM_DEAL = 2;
    private static final int BIG_NUM_DEAL = 3;

    public Bread() {
        super("bread", 1.00, "loaf", 1);
        this.age = 1;
    }

    public Bread(String name, double price, int age) {
        super(name, price, "loaf");
        this.age = age;
        this.quantity = 1;
    }

    public Bread(String name, double price, int age, int quantity) {
        super(name, price, "loaf");
        this.age = age;
        this.quantity = quantity;
    }
    public Bread(String name, double price, int age, int quantity, String unit) {
        super(name, price, unit);
        this.age = age;
        this.quantity = quantity;
    }

    @Override
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - getDiscount();
    }

    @Override
    public double getDiscount() {
        if (age >= DAYS_SMALL_DISCOUNT && age < DAYS_BIG_DISCOUNT) {
            return price * (Math.floor((double) quantity / (SMALL_NUM_DEAL)));
        } else if (age == DAYS_BIG_DISCOUNT) {
            return price * (Math.floor((double) quantity / (BIG_NUM_DEAL)));
        }
        return 0.0;
    }

    public int getAge() {
        return age;
    }
}



