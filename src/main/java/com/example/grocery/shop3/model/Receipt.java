package com.example.grocery.shop3.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Item>      items;

    public Receipt() {
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

    // TODO: is it correct to use here directly the DiscountStrategy classes?
    public String getDiscountRules() {
        return "Discounts:" + new DiscountStrategyBeer() + new DiscountStrategyBread() + new DiscountStrategyVegetable();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt:\n");
        double total = 0.0;
        int itemCount = 1;
        System.out.println("Items list: " + items);

        for (Item item : this.items) {
            receipt.append(itemCount).append(".) ").append(item.toString());
            itemCount++;
            total += item.getTotalAfterDiscount();
            System.out.println(item);
        }
        receipt.append(String.format("   Total after discount: €%.2f\n", total));
        return receipt.toString();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
