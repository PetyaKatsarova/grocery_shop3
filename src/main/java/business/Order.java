package business;

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
        double total = 0.0;
        for (Item item : items) {
            double itemTotal = item.getTotalPrice();
            receipt.append(String.format("%s: €%.2f\n", item.getName(), itemTotal));
            total += itemTotal;
        }
        receipt.append(String.format("Total: €%.2f", total));
        return receipt.toString();
    }
}
