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
    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getTotalAfterDiscount).sum();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("%-6s %-10s %-10s %-10s %-10s %-10s\n", "Qty", "Item", "UnitPrice", "Price", "Discount", "Total"));
        double total = 0.0;
        for (Item item : items) {
            int qty = item instanceof Beer ? ((Beer) item).getQuantity() : (item instanceof Bread ? ((Bread) item).getQuantity() : 1);
            double price = item.getPrice() * qty;
            double discount = item.getDiscount();
            double totalAfterDiscount = item.getTotalAfterDiscount();
            receipt.append(String.format("%-6d %-10s €%-9.2f €%-9.2f €%-9.2f €%-9.2f\n", qty, item.getName(), item.getPrice(), price, discount, totalAfterDiscount));
            total += totalAfterDiscount;
        }
        receipt.append(String.format("\nTotal: €%.2f", total));
        return receipt.toString();
    }
}
