// File: src/main/java/com/grocerystore/model/Order.java

package com.grocerystore.model;

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

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("%-8s %-10s %-12s %-12s %-10s %-16s\n", "amount", "item", "single price", "price", "discount", "totalAfterDiscount"));

        double total = 0.0;
        for (Item item : items) {
            double singlePrice = item.getPrice();
            int amount = 1;
            if (item instanceof Beer) {
                amount = ((Beer) item).getQuantity();
            }
            double price = singlePrice * amount;
            double totalAfterDiscount = item.getTotalPrice();
            double discount = price - totalAfterDiscount;
            receipt.append(String.format("%-8d %-10s €%-11.2f €%-11.2f €%-9.2f €%-15.2f\n", amount, item.getName(), singlePrice, price, discount, totalAfterDiscount));
            total += totalAfterDiscount;
        }
        receipt.append(String.format("\nTotal: €%.2f", total));
        return receipt.toString();
    }
}
