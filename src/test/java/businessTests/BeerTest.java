package businessTests;

//package com.example.grocery_shop3.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.grocery_shop3.model.Beer;

public class BeerTest {

    @Test
    public void testDefaultConstructor() {
        Beer beer = new Beer();
        assertEquals("beer", beer.getName());
        assertEquals(0.5, beer.getPrice());
        assertEquals("bottle", beer.getUnit());
        assertEquals(1, beer.getQuantity());
        assertEquals(Beer.BeerType.DUTCH, beer.getType());
    }

    @Test
    public void testCustomConstructor() {
        Beer beer = new Beer("Custom Beer", 1.0, Beer.BeerType.GERMAN, "pack");
        assertEquals("Custom Beer", beer.getName());
        assertEquals(1.0, beer.getPrice());
        assertEquals("pack", beer.getUnit());
        assertEquals(1, beer.getQuantity());
        assertEquals(Beer.BeerType.GERMAN, beer.getType());
    }

    @Test
    public void testCustomConstructorWithQuantity() {
        Beer beer = new Beer("Custom Beer", 1.0, Beer.BeerType.BELGIUM, 12, "pack");
        assertEquals("Custom Beer", beer.getName());
        assertEquals(1.0, beer.getPrice());
        assertEquals("pack", beer.getUnit());
        assertEquals(12, beer.getQuantity());
        assertEquals(Beer.BeerType.BELGIUM, beer.getType());
    }

    @Test
    public void testInvalidUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Beer("Invalid Beer", 1.0, Beer.BeerType.GERMAN, 6, "invalid");
        });
        assertEquals("Beer unit can be only a pack or a bottle.", exception.getMessage());
    }

    @Test
    public void testGetTotal() {
        Beer beer = new Beer("Beer", 1.0, Beer.BeerType.GERMAN, 6, "pack");
        assertEquals(6.0, beer.getTotal());
    }

    @Test
    public void testGetTotalAfterDiscount_Bottle() {
        Beer beer = new Beer("Beer", 1.0, Beer.BeerType.DUTCH, 8, "bottle");
        assertEquals(8.0, beer.getTotal());
        assertEquals(6.0, beer.getTotalAfterDiscount()); // 8.0 - 2.0 discount
    }

    @Test
    public void testGetTotalAfterDiscount_Pack() {
        Beer beer = new Beer("Beer", 3.5, Beer.BeerType.BELGIUM, 12, "pack");
        assertEquals(42, beer.getTotal());
        assertEquals(6.0, beer.getTotalAfterDiscount()); // 42.0 - 3*12 discount
    }

    @Test
    public void testGetDiscount_Belgium() {
        Beer beer = new Beer("Beer", 1.0, Beer.BeerType.BELGIUM, 12, "bottle");
        assertEquals(6.0, beer.getDiscount()); // 2 packs * 3.0 discount
    }

    @Test
    public void testTotalAfterGetDiscount_Dutch() {
        Beer beer = new Beer("Beer", 10.0, Beer.BeerType.DUTCH, 9, "bottle");
        assertEquals(88.0, beer.getTotalAfterDiscount()); // total is 90 - 6*2 = 88
    }

    @Test
    public void testGetDiscount_German() {
        Beer beer = new Beer("Beer", 1.0, Beer.BeerType.GERMAN, 18, "bottle");
        assertEquals(12.0, beer.getDiscount()); // 3 packs * 4.0 discount
    }

    @Test
    public void testGetDiscount_InsufficientQuantity() {
        Beer beer = new Beer("Beer", 1.0, Beer.BeerType.DUTCH, 5, "bottle");
        assertEquals(0.0, beer.getDiscount()); // Not enough for a pack
    }
}

