package modelTests;

import com.example.grocery_shop3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private Bread bread;
    private Beer beer;
    private Vegetable vegetable;

    @BeforeEach
    void setUp() {
        order = new Order();
        bread = new Bread("bread", 1.0, 3, 2); // 3 items, 2 days old
        beer = new Beer("beer", 2.0, Beer.BeerType.DUTCH, 6, "bottle");
        vegetable = new Vegetable("vegetable", 0.5, 300, "g", 100);
    }

    @Test
    void testAddItem_ValidItems() {
        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        List<Item> items = order.getItems();
        assertEquals(3, items.size());
        assertTrue(items.contains(bread));
        assertTrue(items.contains(beer));
        assertTrue(items.contains(vegetable));
    }

    @Test
    void testAddItem_InvalidItem() {
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
    void testGetItemsPrices() {
        String prices = order.getItemsPrices();
        assertNotNull(prices);
        System.out.println(prices);
        assertTrue(prices.contains("Bread €1,00"));
        assertTrue(prices.contains("Veg €1,00 per 100g"));
        assertTrue(prices.contains("Beer €0,50 per bottle"));
    }


    @Test
    void testGetDiscountRules() {
        String discountRules = order.getDiscountRules();
        assertNotNull(discountRules);
        assertTrue(discountRules.contains("Discounts:"));
        assertTrue(discountRules.contains("Beer(6 bottles): Belgium -€3.00; Dutch - €2.00; German -€4.00; "));
        assertTrue(discountRules.contains("Bread(3 - 5 days old): buy 1 get 2; (6 days old): buy 1 get 3; "));
        assertTrue(discountRules.contains("Vegetables(1 - 100g): 5%; (101 - 500g): 7%;Over 500g: 10%"));
    }

    @Test
    void testGenerateReceipt_EmptyOrder() {
        String receipt = order.generateReceipt();
        assertTrue(receipt.contains("Receipt:"));
        assertTrue(receipt.contains("Total after discount: €0,00"));
    }

    @Test
    void testGenerateReceipt_NonEmptyOrder() {
        order.addItem(bread);
        order.addItem(beer);
        order.addItem(vegetable);

        String receipt = order.generateReceipt();
        assertNotNull(receipt);
        System.out.println(receipt);
        assertTrue(receipt.contains("Receipt:"));
        assertTrue(receipt.contains("1. 2 x bread €2,00"));
        assertTrue(receipt.contains("2. 6 x beer €12,00"));
        assertTrue(receipt.contains("3. 300 x vegetable €1,50"));
        assertTrue(receipt.contains("Total after discount:"));
    }

    @Test
    void testGenerateReceipt_LargeOrder() {
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
    void testGenerateReceipt_WithDiscounts() {
        Bread bread = new Bread("bread", 1.0, 6, 4); // Applying discount rule

        order.addItem(bread);

        String receipt = order.generateReceipt();
        assertTrue(receipt.contains("Discount: €"));
    }

    @Test
    void testTotalAfterDiscount_WithDiscount() {
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
    void testTotalAfterDiscount_WithoutDiscount() {
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
    void testGetItems() {
        Bread bread = new Bread("bread", 1.0, 2);
        order.addItem(bread);
        List<Item> items = order.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    void testSetItems() {
        Bread bread = new Bread("bread", 1.0, 2);
        List<Item> items = new ArrayList<>();
        items.add(bread);
        order.setItems(items);
        assertEquals(items, order.getItems());
    }
}
