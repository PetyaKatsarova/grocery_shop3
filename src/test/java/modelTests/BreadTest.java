package modelTests;

import org.junit.jupiter.api.Test;
import com.example.grocery_shop3.model.Bread;
import static org.junit.jupiter.api.Assertions.*;

public class BreadTest {

    @Test
    public void testDefaultConstructor() {
        Bread bread = new Bread();
        assertEquals("bread", bread.getName());
        assertEquals(1.00, bread.getPrice());
        assertEquals("loaf", bread.getUnit());
        assertEquals(1, bread.getQuantity());
        assertEquals(1, bread.getAge());
    }

    @Test
    public void testCustomConstructor() {
        Bread bread = new Bread("Custom Bread", 1.50, 2);
        assertEquals("Custom Bread", bread.getName());
        assertEquals(1.50, bread.getPrice());
        assertEquals("loaf", bread.getUnit());
        assertEquals(1, bread.getQuantity());
        assertEquals(2, bread.getAge());
    }

    @Test
    public void testCustomConstructorWithQuantity() {
        Bread bread = new Bread("Custom Bread", 1.50, 2, 5);
        assertEquals("Custom Bread", bread.getName());
        assertEquals(1.50, bread.getPrice());
        assertEquals("loaf", bread.getUnit());
        assertEquals(5, bread.getQuantity());
        assertEquals(2, bread.getAge());
    }

    @Test
    public void testCustomConstructorWithUnit() {
        Bread bread = new Bread("Custom Bread", 1.50, 2, 5, "bag");
        assertEquals("Custom Bread", bread.getName());
        assertEquals(1.50, bread.getPrice());
        assertEquals("bag", bread.getUnit());
        assertEquals(5, bread.getQuantity());
        assertEquals(2, bread.getAge());
    }

    @Test
    public void testGetTotal() {
        Bread bread = new Bread("Bread", 1.00, 3, 5);
        assertEquals(5.00, bread.getTotal());
    }

    @Test
    public void testGetDiscount_NoDiscount() {
        Bread bread = new Bread("Bread", 1.00, 2, 5);
        assertEquals(0.0, bread.getDiscount());
    }

    @Test
    public void testGetDiscount_SmallDiscount() {
        Bread bread = new Bread("Bread", 1.00, 3, 5);
        assertEquals(2.0, bread.getDiscount()); // 5 / 2 = 2 packs, discount 2 * 1.00
    }

    @Test
    public void testGetDiscount_BigDiscount() {
        Bread bread = new Bread("Bread", 1.00, 6, 5);
        assertEquals(1.0, bread.getDiscount()); // 5 / 3 = 1 pack, discount 1 * 1.00
    }

    @Test
    public void testGetDiscount_AgeAboveBigDiscount() {
        Bread bread = new Bread("Bread", 1.00, 6, 6);
        assertEquals(2.0, bread.getDiscount()); // 6 / 3 = 2 packs, discount 2 * 1.00
    }

    @Test
    public void testGetTotalAfterDiscount_NoDiscount() {
        Bread bread = new Bread("Bread", 1.00, 2, 5);
        assertEquals(5.00, bread.getTotalAfterDiscount());
    }

    @Test
    public void testGetTotalAfterDiscount_SmallDiscount() {
        Bread bread = new Bread("Bread", 1.00, 3, 8);
        assertEquals(4.00, bread.getTotalAfterDiscount());
    }

    @Test
    public void testGetTotalAfterDiscount_BigDiscount() {
        Bread bread = new Bread("Bread", 1.00, 6, 5);
        assertEquals(4.00, bread.getTotalAfterDiscount());
    }

    @Test
    public void testGetTotalAfterDiscount_AgeAboveBigDiscount() {
        Bread bread = new Bread("Bread", 1.00, 6, 6);
        assertEquals(4.00, bread.getTotalAfterDiscount()); // 6.00 - 2.00
    }

    @Test
    public void testSetAge_TooOld() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bread("Old Bread", 1.00, 7, 1);
        });
        assertEquals("Bread is too old to sell, over/equal 7 days old", exception.getMessage());
    }

    @Test
    public void testSetAge_ValidAge() {
        Bread bread = new Bread("Fresh Bread", 1.00, 5, 1);
        assertEquals(5, bread.getAge());
    }
}
