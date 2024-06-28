// File: src/main/java/com/grocerystore/model/Order.java

package com.example.grocery_shop3.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!(item instanceof Bread) && !(item instanceof Beer) && !(item instanceof Vegetable)) {
            throw new IllegalArgumentException("We have only bread, beer or vegetable.");
        }
        items.add(item);
    }

    /**
     * Example order w/ prices:
     * Bread €1,00, Veg €1,00 per 100g, Beer €0,50 per bottle
     * 1. 3 x Bread (three days old) €2,00
     * 2. 200g Vegetables €1,86
     * 3. 6 x Dutch Beers €2,00
     *    Total: €5,86
     *    ○ Given an Order, return the total cost with discounts applied,
     * and a receipt that breaks down the prices
     *  List the current discount rules
     * ○ List the current prices per item
     */

    public String getItemsPrices() {
        Bread bread = new Bread();
        Beer beer = new Beer();
        Vegetable vegetable = new Vegetable();
        return String.format("Bread €%.2f, Veg €%.2f per %.0f%s, Beer €%.2f per %s\n", bread.getPrice(), vegetable.getPrice(), vegetable.getWeight(),
                vegetable.getUnit(), beer.getPrice(), beer.getUnit());
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getTotalAfterDiscount).sum();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        double total = 0.0;
        int itemCount = 1;
        for (Item item : items) {
            receipt.append(String.format("%d. %d x %s €%.2f\n",
                    itemCount, item.getQuantity(), item.getName(), item.getTotalAfterDiscount())); // TODO: discount logic is wrong!! ??  * item.getQuantity()
            itemCount++;
            total += item.getTotalAfterDiscount();
        }
        receipt.append(String.format("   Total: €%.2f", total));
        return receipt.toString();
    }

}
