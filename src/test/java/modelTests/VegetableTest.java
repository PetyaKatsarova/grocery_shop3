package modelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.grocery.shop3.model.Vegetable;

public class VegetableTest {
    @Test
    public void testSetWeightForPrice_InvalidValue() {
        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            vegetable.setWeight_for_price(0);
        });
        assertEquals("the weight for price should be higher than 0", exception.getMessage());
    }

    @Test
    public void testSetWeightForPrice_NegativeValue() {
        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            vegetable.setWeight_for_price(-1);
        });
        assertEquals("the weight for price should be higher than 0", exception.getMessage());
    }

    @Test
    public void testGetTotal() {
        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
        assertEquals(4.00, vegetable.getTotal());
    }

    @Test
    public void testGetTotalAfterDiscount_SmallDiscount() {
        Vegetable vegetable = new Vegetable("Lettuce", 1.80, 1, "kg", 1);
        assertEquals(1.71, vegetable.getTotalAfterDiscount(), 0.01); // 1.80 - 0.05 * 1.80
    }

    @Test
    public void testGetTotalAfterDiscount_MediumDiscount() {
        Vegetable vegetable = new Vegetable("Cucumber", 1.00, 400, "g", 100);
        assertEquals(3.72, vegetable.getTotalAfterDiscount(), 0.01); // 4.00 - 0.07 * 4.00
    }

    @Test
    public void testGetTotalAfterDiscount_BigDiscount() {
        Vegetable vegetable = new Vegetable("Pumpkin", 1.00, 600, "g", 100);
        assertEquals(5.40, vegetable.getTotalAfterDiscount(), 0.01); // 6.00 - 0.10 * 6.00
    }

    @Test
    public void testInvalidQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Vegetable("Broccoli", 1.00, -1, "g", 100);
        });
        assertEquals("Quantity cant be 0 or negative", exception.getMessage());
    }

    @Test
    public void testInvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Vegetable("Broccoli", 0, 100, "g", 100);
        });
        assertEquals("Price cant be 0 or negative", exception.getMessage());
    }

    @Test
    public void testEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Vegetable("", 1.00, 100, "g", 100);
        });
        assertEquals("Name can't be empty string", exception.getMessage());
    }

    @Test
    public void testEmptyUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Vegetable("Broccoli", 1.00, 100, "", 100);
        });
        assertEquals("Unit can't be empty string", exception.getMessage());
    }
}

