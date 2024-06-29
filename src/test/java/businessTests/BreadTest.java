//package businessTests;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import com.example.grocery_shop3.model.Bread;
//
//class BreadTest {
//
//    @Test
//    void testBreadNoDiscount() {
//        Bread bread = new Bread("Fresh Bread", 1.00, 1);
//        assertEquals(1.00, bread.getTotalPrice(), 0.01);
//    }
//
//    @Test
//    void testBreadBuy1Take2() {
//        Bread bread = new Bread("3 Days Old Bread", 1.00, 3);
//        assertEquals(0.50, bread.getTotalPrice(), 0.01);
//    }
//
//    @Test
//    void testBreadBuy1Take3() {
//        Bread bread = new Bread("6 Days Old Bread", 1.00, 6);
//        assertEquals(0.33, bread.getTotalPrice(), 0.01);
//    }
//
//    @Test
//    void testBreadOlderThan6Days() {
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            new Bread("7 Days Old Bread", 1.00, 7);
//        });
//        assertEquals("Bread older than 6 days cannot be added.", thrown.getMessage());
//    }
//}
