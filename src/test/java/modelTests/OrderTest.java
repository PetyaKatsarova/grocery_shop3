package modelTests;

import com.example.grocery_shop3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testAddItem_ValidItems() {
        Bread bread = new Bread("bread", 1.0, 2);
        Beer beer = new Beer("beer", 2.0, Beer.BeerType.DUTCH, 6, "bottle");
        Vegetable vegetable = new Vegetable("vegetable", 1.0, 200, "g", 100);

        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        assertEquals(3, order.getItems().size());
    }

    @Test
    public void testAddItem_InvalidItem() {
        Item invalidItem = new Item("Invalid", 1.0, "unit") {
            @Override
            public double getTotal() {
                return 0;
            }

            @Override
            public double getDiscount() {
                return 0;
            }

            @Override
            public double getTotalAfterDiscount() {
                return 0;
            }
        };

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addItem(invalidItem);
        });

        assertEquals("We have only bread, beer or vegetable.", exception.getMessage());
    }

    @Test
    public void testGetItemsPrices() {
        String prices = order.getItemsPrices();
        assertTrue(prices.contains("Bread €"));
        assertTrue(prices.contains("Veg €"));
        assertTrue(prices.contains("Beer €"));
    }

    @Test
    public void testGetDiscountRules() {
        String discounts = order.getDiscountRules();
        assertTrue(discounts.contains("Discounts:"));
        assertTrue(discounts.contains("Beer"));
        assertTrue(discounts.contains("Bread"));
        assertTrue(discounts.contains("Vegetables"));
    }

    @Test
    public void testGenerateReceipt_EmptyOrder() {
        String receipt = order.generateReceipt();
        assertTrue(receipt.contains("Receipt:"));
        assertTrue(receipt.contains("Total after discount: €0.00"));
    }

    @Test
    public void testGenerateReceipt_NonEmptyOrder() {
        Bread bread = new Bread("bread", 1.0, 2, 2);
        Beer beer = new Beer("beer", 2.0, Beer.BeerType.DUTCH, 6, "bottle");
        Vegetable vegetable = new Vegetable("vegetable", 1.0, 200, "g", 100);

        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        String receipt = order.generateReceipt();

        assertTrue(receipt.contains("Receipt:"));
        assertTrue(receipt.contains("1. 2 x bread"));
        assertTrue(receipt.contains("2. 6 x beer"));
        assertTrue(receipt.contains("3. 200 x vegetable"));
        assertTrue(receipt.contains("Total after discount:"));
    }

    @Test
    public void testGenerateReceipt_LargeOrder() {
        for (int i = 0; i < 100; i++) {
            Bread bread = new Bread("bread", 1.0, 2, i + 1);
            order.addItem(bread);
        }

        String receipt = order.generateReceipt();
        assertTrue(receipt.contains("Receipt:"));
        assertTrue(receipt.contains("Total after discount:"));
        assertEquals(100, order.getItems().size());
    }

    @Test
    public void testGenerateReceipt_WithDiscounts() {
        Bread bread = new Bread("bread", 1.0, 6, 4); // Applying discount rule

        order.addItem(bread);

        String receipt = order.generateReceipt();
        assertTrue(receipt.contains("Discount:"));
    }

    @Test
    public void testTotalAfterDiscount_WithDiscount() {
        Bread bread = new Bread("bread", 1.0, 6, 4); // Applying discount rule
        Beer beer = new Beer("beer", 2.0, Beer.BeerType.DUTCH, 12, "bottle"); // Applying discount rule
        Vegetable vegetable = new Vegetable("vegetable", 1.0, 600, "g", 100); // Applying discount rule

        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        double expectedTotal = (bread.getTotal() - bread.getDiscount()) +
                (beer.getTotal() - beer.getDiscount()) +
                (vegetable.getTotal() - vegetable.getDiscount());

        double actualTotal = order.getItems().stream()
                .mapToDouble(Item::getTotalAfterDiscount)
                .sum();

        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void testTotalAfterDiscount_WithoutDiscount() {
        Bread bread = new Bread("bread", 1.0, 2, 1); // No discount
        Beer beer = new Beer("beer", 2.0, Beer.BeerType.DUTCH, 5, "bottle"); // No discount
        Vegetable vegetable = new Vegetable("vegetable", 1.0, 50, "g", 100); // No discount

        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        double expectedTotal = bread.getTotalAfterDiscount() + beer.getTotalAfterDiscount() + vegetable.getTotalAfterDiscount();
        double actualTotal = order.getItems().stream()
                .mapToDouble(Item::getTotalAfterDiscount)
                .sum();

        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void testGetItems() {
        Bread bread = new Bread("bread", 1.0, 2);
        order.addItem(bread);
        List<Item> items = order.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    public void testSetItems() {
        Bread bread = new Bread("bread", 1.0, 2);
        List<Item> items = new ArrayList<>();
        items.add(bread);
        order.setItems(items);
        assertEquals(items, order.getItems());
    }
}
