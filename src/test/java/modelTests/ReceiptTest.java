package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.grocery.shop3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReceiptTest {

    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        receipt = new Receipt();
    }

    @Test
    public void testAddItem_ValidItems() {
        Item beer = new Beer("Beer", 2.50, Beer.BeerType.DUTCH, 6, "bottle");
        Item vegetable = new Vegetable("Carrot", 1.00, 500, "g", 100);  // 500g vegetable
        Item bread = new Bread("Bread", 1.50, 2, 1);  // 2 loaves of bread
        receipt.addItem(beer);
        receipt.addItem(vegetable);
        receipt.addItem(bread);

        assertEquals(3, receipt.getItems().size());
        assertTrue(receipt.getItems().contains(beer));
        assertTrue(receipt.getItems().contains(vegetable));
        assertTrue(receipt.getItems().contains(bread));
    }

    @Test
    public void testAddItem_InvalidItem() {
        Item invalidItem = new Item("Invalid Item", 5.0, "unit", new DiscountStrategyBeer()) {
            @Override
            public double getTotal() {
                return 0;
            }

            @Override
            public double getTotalAfterDiscount() {
                return 0;
            }

            @Override
            public String toString() {
                return "Invalid Item";
            }
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            receipt.addItem(invalidItem);
        });
        assertEquals("We have only bread, beer or vegetable.", exception.getMessage());
    }

    @Test
    public void testGetItemsPrices() {
        assertEquals("Bread €1,00, Veg €1,00 per 100g, Beer €0,50 per bottle\n", receipt.getItemsPrices());
    }

    @Test
    public void testGetDiscountRules() {
        String discountRules = "Discounts:" + new DiscountStrategyBeer() + new DiscountStrategyBread() + new DiscountStrategyVegetable();
        assertEquals(discountRules, receipt.getDiscountRules());
    }

    // checking correct calculation of discounts strategy items is done in the items tests and discount strategy tests
    @Test
    public void testGenerateReceipt_WithItems() {
        Item beer = new Beer("Beer", 2.50, Beer.BeerType.DUTCH, 6, "bottle");
        Item bread = new Bread("Bread", 1.00, 3, 2);
        receipt.addItem(beer);
        receipt.addItem(bread);

        String generatedReceipt = receipt.generateReceipt();
        assertTrue(generatedReceipt.contains("Receipt:"));
        assertTrue(generatedReceipt.contains("Total after discount"));
    }

    @Test
    public void testGenerateReceipt_NoItems() {
        String generatedReceipt = receipt.generateReceipt();
        assertTrue(generatedReceipt.contains("Receipt:"));
        assertTrue(generatedReceipt.contains("Total after discount: €0.00"));
        System.out.println(generatedReceipt);
    }


    // Test generating a receipt with edge case (large quantities and prices)
    @Test
    public void testGenerateReceipt_LargeQuantityAndPrice() {
        Item vegetable = new Vegetable("Potato", 1000.00, 10000, "g", 100);
        receipt.addItem(vegetable);
        String generatedReceipt = receipt.generateReceipt();
        assertTrue(generatedReceipt.contains("Total after discount:"));
        assertTrue(generatedReceipt.contains("Potato"));
    }

    // Test adding multiple valid items and checking item list size
    @Test
    public void testAddMultipleValidItems() {
        Item beer = new Beer("Beer", 2.50, Beer.BeerType.DUTCH, 6, "bottle");
        Item bread = new Bread("Bread", 1.00, 3, 2);
        Item vegetable = new Vegetable("Carrot", 1.00, 500, "g", 100);

        receipt.addItem(beer);
        receipt.addItem(bread);
        receipt.addItem(vegetable);

        assertEquals(3, receipt.getItems().size());
        assertTrue(receipt.getItems().contains(beer));
        assertTrue(receipt.getItems().contains(bread));
        assertTrue(receipt.getItems().contains(vegetable));
    }
}
