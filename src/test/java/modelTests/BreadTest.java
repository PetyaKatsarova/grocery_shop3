package modelTests;

import org.junit.jupiter.api.Test;
import com.example.grocery.shop3.model.Bread;
import static org.junit.jupiter.api.Assertions.*;

public class BreadTest {
    @Test
    public void testGetTotal() {
        Bread bread = new Bread("Bread", 1.00, 3, 5);
        assertEquals(5.00, bread.getTotal());
    }
//
//    @Test
//    public void testGetDiscount_NoDiscount() {
//        Bread bread = new Bread("Bread", 1.00, 2, 5);
//        assertEquals(0.0, bread.getTotalAfterDiscount());
//    }

//    @Test
//    public void testGetDiscount_SmallDiscount() {
//        Bread bread = new Bread("Bread", 1.00, 3, 5);
//        assertEquals(2.0, bread.getDiscount()); // 5 / 2 = 2 packs, discount 2 * 1.00
//    }
//
//    @Test
//    public void testGetDiscount_BigDiscount() {
//        Bread bread = new Bread("Bread", 1.00, 6, 5);
//        assertEquals(1.0, bread.getDiscount()); // 5 / 3 = 1 pack, discount 1 * 1.00
//    }
//
//    @Test
//    public void testGetDiscount_AgeAboveBigDiscount() {
//        Bread bread = new Bread("Bread", 1.00, 6, 6);
//        assertEquals(2.0, bread.getDiscount()); // 6 / 3 = 2 packs, discount 2 * 1.00
//    }

    @Test
    public void testGetTotalAfterDiscount_NoDiscount() {
        Bread bread = new Bread("Bread", 1.00, 2, 5);
        assertEquals(5.00, bread.getTotalAfterDiscount());
    }

    @Test
    public void testGetTotalAfterDiscount_BiggerThanTotal() {
        Bread bread = new Bread("Bread", 1.00, 6, 9);
        assertEquals(6.00, bread.getTotalAfterDiscount()); //
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
