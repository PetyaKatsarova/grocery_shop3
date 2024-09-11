package modelTest;

import org.junit.jupiter.api.Test;
import com.example.grocery.shop3.model.Bread;
import static org.junit.jupiter.api.Assertions.*;

public class BreadTest {
    /**
     * for curr logic can't get discount bigger than actual bill
     * */

    @Test
    public void testGetTotal() {
        Bread bread = new Bread("Bread", 1.00, 3, 5);
        assertEquals(5.00, bread.getTotal());
    }

    @Test
    public void testGetTotalAfterDiscount_NoDiscount() {
        Bread bread = new Bread("Bread", 1.00, 2, 5);
        assertEquals(5.00, bread.getTotalAfterDiscount());
    }

    @Test
    public void testGetTotalAfterDiscount_SmallDiscount() {
        Bread bread = new Bread("Bread", 1.00, 4, 5);
        assertEquals(3.00, bread.getTotalAfterDiscount()); // 3
    }

    @Test
    public void testGetTotalAfterDiscount_BigDiscount() {
        Bread bread = new Bread("Bread", 1.00, 6, 13);
        assertEquals(5.00, bread.getTotalAfterDiscount()); // 13 - (math.floor(13/3)*2) = 13 - 8 = 5
    }

    @Test
    public void testSetAge_TooOld() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bread("Old Bread", 1.00, 7, 1);
        });
        assertEquals("Bread is too old to sell, over/equal 7 days old", exception.getMessage());
    }

    @Test
    public void testSetAge_NegativeInt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bread("Fresh Bread", 1.00, -5, 1);
        });
        assertEquals("Bread age should be a positive number", exception.getMessage());
    }
}
