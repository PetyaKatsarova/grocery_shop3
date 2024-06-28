package com.example.grocery_shop3.model;

public class Bread extends Item {
    private int             age;
    private int             quantity;
    private double          small_num_free = 1; // buy 1 get 2
    private double          big_num_free = 2; // buy 1 get 3
    private int             days_small_discount = 3; // TODO: add all those to be set from constructor
    private int             days_big_discount = 6;

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
    public double getTotal() {
        return price * quantity;
    }

//    TODO: do we need this??? BELLOW
    @Override
    public double getTotalAfterDiscount() {
        return getTotal() - getDiscount();
    }

    /**
     * Returns discount applied according to conditions: if the bread is:
     * one day old or newer:no discount.
     * 3 days old: “buy 1 take 2” discount.
     * 6 days old: “pay 1 take 3”.
     * older than 6 days cannot be added to orders.
     * */
//    TODO: QUESTIONS: WHAT IF BREAD IS OLD 2 DAYS? ANY DISCOUNT? OR 4 AND 5 DAYS OLD?
    // todo: logic is wrong: fix it
    @Override
    public double getDiscount() {
        if (age >= 3 && age < 6) {
            return price * (Math.floor((double) quantity / 2)); // if 5 breads: 5 / 2 = 2, leave the decimal; getting 1 free
        } else if (age == 6) {
            return price * (Math.floor((double) quantity / 3)) * 2; // if 5 breads: 5 / 2 = 2, leave the decimal; getting 2 free
        }
        return 0.0;
    }
}



