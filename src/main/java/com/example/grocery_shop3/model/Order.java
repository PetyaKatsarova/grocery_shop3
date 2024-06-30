package com.example.grocery_shop3.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item>      items;
    private static String   BEER_DISCOUNT =  "\nBeer(6 bottles): Belgium -€3.00; Dutch - €2.00; German -€4.00; ";
    private static String   BREAD_DISCOUNT = "\nBread(3 - 5 days old): buy 1 get 2; (6 days old): buy 1 get 3; ";
    private static String   VEGETABLE_DISCOUNT = "\nVegetables(1 - 100g): 5%; (101 - 500g): 7%;Over 500g: 10% \n";

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!(item instanceof Bread) && !(item instanceof Beer) && !(item instanceof Vegetable)) {
            throw new IllegalArgumentException("We have only bread, beer or vegetable.");
        }
        items.add(item);
    }

    public String getItemsPrices() {
        Bread bread = new Bread();
        Beer beer = new Beer();
        Vegetable vegetable = new Vegetable();
        return String.format("Bread €%.2f, Veg €%.2f per %.0f%s, Beer €%.2f per %s\n", bread.getPrice(), vegetable.getPrice(), vegetable.getWeight_for_price(),
                vegetable.getUnit(), beer.getPrice(), beer.getUnit());
    }

    public String getDiscountRules() {
        return "Discounts:" + BEER_DISCOUNT + BREAD_DISCOUNT + VEGETABLE_DISCOUNT;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt:\n");
        double total = 0.0;
        int itemCount = 1;
        for (Item item : items) {
            receipt.append(String.format("%d. %d x %s €%.2f\n",
                    itemCount, item.getQuantity(), item.getName(), item.getTotal()));
            receipt.append(String.format("   Discount: €%.2f\n", item.getDiscount()));
            itemCount++;
            total += item.getTotalAfterDiscount();
        }
        receipt.append(String.format("   Total after discount: €%.2f\n", total));
        return receipt.toString();
    }
}
