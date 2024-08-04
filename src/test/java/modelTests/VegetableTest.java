//package modelTests;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import com.example.grocery.shop3.model.Vegetable;
//
//public class VegetableTest {
//
//    @Test
//    public void testDefaultConstructor() {
//        Vegetable vegetable = new Vegetable();
//        assertEquals("vegetable", vegetable.getName());
//        assertEquals(1.00, vegetable.getPrice());
//        assertEquals("g", vegetable.getUnit());
//        assertEquals(100, vegetable.getQuantity());
//        assertEquals(100.00, vegetable.getWeight_for_price());
//    }
//
//    @Test
//    public void testCustomConstructor() {
//        Vegetable vegetable = new Vegetable("Carrot", 1.50, 150.00, 100.00);
//        assertEquals("Carrot", vegetable.getName());
//        assertEquals(1.50, vegetable.getPrice());
//        assertEquals("g", vegetable.getUnit());
//        assertEquals(100, vegetable.getQuantity());
//        assertEquals(100.00, vegetable.getWeight_for_price());
//    }
//
//    @Test
//    public void testCustomConstructorWithUnit() {
//        Vegetable vegetable = new Vegetable("Potato", 2.00, 500, "kg", 1000.00);
//        assertEquals("Potato", vegetable.getName());
//        assertEquals(2.00, vegetable.getPrice());
//        assertEquals("kg", vegetable.getUnit());
//        assertEquals(500, vegetable.getQuantity());
//        assertEquals(1000.00, vegetable.getWeight_for_price());
//    }
//
//    @Test
//    public void testSetWeightForPrice_ValidValue() {
//        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
//        vegetable.setWeight_for_price(200.00);
//        assertEquals(200.00, vegetable.getWeight_for_price());
//    }
//
//    @Test
//    public void testSetWeightForPrice_InvalidValue() {
//        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            vegetable.setWeight_for_price(0);
//        });
//        assertEquals("the weight for price should be higher than 0", exception.getMessage());
//    }
//
//    @Test
//    public void testSetWeightForPrice_NegativeValue() {
//        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            vegetable.setWeight_for_price(-1);
//        });
//        assertEquals("the weight for price should be higher than 0", exception.getMessage());
//    }
//
//    @Test
//    public void testGetTotal() {
//        Vegetable vegetable = new Vegetable("Tomato", 2.00, 200, "g", 100);
//        assertEquals(4.00, vegetable.getTotal());
//    }
//
//    @Test
//    public void testGetTotalAfterDiscount_SmallDiscount_1kg_Price_1_80() {
//        Vegetable vegetable = new Vegetable("Lettuce", 1.80, 1, "kg", 1);
//        assertEquals(1.71, vegetable.getTotalAfterDiscount(), 0.01); // 1.80 - 0.05 * 1.80
//    }
//
//
//    @Test
//    public void testGetTotalAfterDiscount_MediumDiscount() {
//        Vegetable vegetable = new Vegetable("Cucumber", 1.00, 400, "g", 100);
//        assertEquals(3.72, vegetable.getTotalAfterDiscount(), 0.01); // 4.00 - 0.07 * 4.00
//    }
//
//    @Test
//    public void testGetTotalAfterDiscount_BigDiscount() {
//        Vegetable vegetable = new Vegetable("Pumpkin", 1.00, 600, "g", 100);
//        assertEquals(5.40, vegetable.getTotalAfterDiscount(), 0.01); // 6.00 - 0.10 * 6.00
//    }
//
//    @Test
//    public void testGetDiscount_SmallDiscount1() {
//        Vegetable vegetable = new Vegetable("Onion", 1.00, 50, "g", 100);
//        assertEquals((1.00*50/100)*0.05, vegetable.getDiscount());
//    }
//
//    @Test
//    public void testGetDiscount_SmallDiscount2() {
//        Vegetable vegetable = new Vegetable("Pepper", 1.00, 100, "g", 100);
//        assertEquals(1 * 0.05, vegetable.getDiscount());
//    }
//
//    @Test
//    public void testGetDiscount_MediumDiscount() {
//        Vegetable vegetable = new Vegetable("Zucchini", 1.00, 101, "g", 100);
//        assertEquals((1*101/100)*0.07, vegetable.getDiscount(), 0.01); // 1.01 is the total (price * quantity / weight_for_price), and 0.07 is the medium discount
//    }
//
//
//    @Test
//    public void testGetDiscount_BigDiscount() {
//        Vegetable vegetable = new Vegetable("Spinach", 1.00, 700, "g", 100);
//        assertEquals((1.0*700/100)*0.1, vegetable.getDiscount());
//    }
//
//    @Test
//    public void testInvalidQuantity() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new Vegetable("Broccoli", 1.00, -1, "g", 100);
//        });
//        assertEquals("Quantity cant be 0 or negative", exception.getMessage());
//    }
//
//    @Test
//    public void testInvalidPrice() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new Vegetable("Broccoli", 0, 100, "g", 100);
//        });
//        assertEquals("Price cant be 0 or negative", exception.getMessage());
//    }
//
//    @Test
//    public void testEmptyName() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new Vegetable("", 1.00, 100, "g", 100);
//        });
//        assertEquals("Name can't be empty string", exception.getMessage());
//    }
//
//    @Test
//    public void testEmptyUnit() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new Vegetable("Broccoli", 1.00, 100, "", 100);
//        });
//        assertEquals("Unit can't be empty string", exception.getMessage());
//    }
//}
//
