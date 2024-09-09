package modelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.grocery.shop3.model.Beer;

public class BeerTest {
    @Test
    public void testTotalCalculation() {
        Beer beer = new Beer("German Beer", 3, Beer.BeerType.GERMAN, 42, "bottle");
        assertEquals(126.0, beer.getTotal()); // 3.0 * 42 = 126.0
    }

    @Test
    public void testDiscountPerPack() {
        Beer beer = new Beer("Dutch Beer", 5.0, Beer.BeerType.DUTCH, 3, "pack");
        assertEquals(15.0, beer.getTotal()); // 5.0 * 3 = 15.0 before discount
        assertEquals(9, beer.getTotalAfterDiscount()); // 15.0 - 6 (-2e * 3packs) = 9
    }

    @Test
    public void testDiscountNoDiscount() {
        Beer beer = new Beer("Dutch Beer", 5.0, Beer.BeerType.DUTCH, 3, "bottle");
        assertEquals(15.0, beer.getTotal()); // 5.0 * 3 = 15.0 before discount
        assertEquals(15.0, beer.getTotalAfterDiscount()); // 15.0 no discount for 3 beers
    }

    @Test
    public void testDiscountBiggerThanPrice() { // G discount is 4 euros per pack
        Beer beer = new Beer("Dutch Beer", 3.0, Beer.BeerType.GERMAN, 3, "pack");
        assertEquals(9.0, beer.getTotal()); // 9.0
        assertEquals(0, beer.getTotalAfterDiscount()); // 9.0 - 12 (-4e * 3packs) = -3.0 but returns 0: give for free
    }

    @Test
    public void testInvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Beer("Invalid Beer", 0, Beer.BeerType.GERMAN, "bottle");
        });
        assertEquals("Price cant be 0 or negative", exception.getMessage());
    }

    @Test
    public void testInvalidQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Beer("Invalid Beer", 5.0, Beer.BeerType.BELGIUM, -1, "bottle");
        });
        assertEquals("Quantity cant be 0 or negative", exception.getMessage());
    }

    @Test
    public void testInvalidUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Beer("Invalid Beer", 5.0, Beer.BeerType.BELGIUM, 6, "invalidUnit");
        });
        assertEquals("Beer can only be sold in bottles or packs.", exception.getMessage());
    }

    @Test
    public void testUnitCorrectSpellingHelper() {
        Beer beerBottles = new Beer("Dutch Beer", 2.0, Beer.BeerType.DUTCH, 1, "bottle");
        assertEquals("bottles", beerBottles.unitCorrectSpellingHelper());

        Beer beerPacks = new Beer("Belgium Beer", 2.0, Beer.BeerType.BELGIUM, 1, "pack");
        assertEquals("packs", beerPacks.unitCorrectSpellingHelper());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Beer("Invalid Beer", 2.0, Beer.BeerType.BELGIUM, 1, "invalidUnit").unitCorrectSpellingHelper();
        });
        assertEquals("Beer can only be sold in bottles or packs.", exception.getMessage());
    }

    @Test
    public void testToString() {
        Beer beer = new Beer("German Beer", 3.0, Beer.BeerType.GERMAN, 4, "bottle");
        String expectedString = "4 bottles x German Beer €12,00\n   Discount: €0,00\n"; // -4 euros discount per pack(6 beers)
        assertEquals(expectedString, beer.toString());
    }

    @Test
    public void testEdgeCaseLargeQuantity() {
        Beer beer = new Beer("Dutch Beer", 2.0, Beer.BeerType.DUTCH, 1000000, "bottle");
        assertEquals(2000000.0, beer.getTotal()); // 2.0 * 1,000,000
        assertEquals(1666668, beer.getTotalAfterDiscount()); // 2 million - ( 1million/6 = 166 666)*2 = 2m - 333_332 = 1_666_668
    }
}
