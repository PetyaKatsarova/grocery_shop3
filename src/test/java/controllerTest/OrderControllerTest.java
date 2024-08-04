package controllerTest;

import com.example.grocery.shop3.controller.OrderController;
import com.example.grocery.shop3.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private Order order;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGameTester() {
        String response = orderController.APITester();
        assertEquals("yahoo, r u ready to order ur groceries? this is a test :)", response);
    }

    @Test
    public void testGenerateReceipt_NullOrder() {
        ResponseEntity<String> response = orderController.generateReceipt(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Order not found", response.getBody());
    }

    @Test
    public void testGenerateReceipt_ValidOrder() {
        when(order.getItemsPrices()).thenReturn("Items prices: ");
        when(order.generateReceipt()).thenReturn("Receipt generated: ");
        when(order.getDiscountRules()).thenReturn("Discount rules: ");

        ResponseEntity<String> response = orderController.generateReceipt(order);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Items prices: Receipt generated: Discount rules: ", response.getBody());
    }

    @Test
    public void testGetDefaultPrices() {
        when(order.getItemsPrices()).thenReturn("Items prices: ");
        String response = orderController.getDefaultPrices();
        assertEquals("Bread €1,00, Veg €1,00 per 100g, Beer €0,50 per bottle\n", response);
    }

    @Test
    public void testGetDefaultDiscounts() {
        when(order.getDiscountRules()).thenReturn("Discount rules:");
        String response = orderController.getDefaultDiscounts();
        assertTrue(response.contains("Discounts:"));
    }

    @Test
    public void testGetDefaultPrices_NullOrder() {
        when(order.getItemsPrices()).thenReturn(null);
        String response = orderController.getDefaultPrices();
        assertEquals("Bread €1,00, Veg €1,00 per 100g, Beer €0,50 per bottle\n", response);
    }

    @Test
    public void testGetDefaultDiscounts_NullOrder() {
        when(order.getDiscountRules()).thenReturn(null);
        String response = orderController.getDefaultDiscounts();
        System.out.println(response);
        assertTrue(response.contains("Discounts:\n" +
                "Beer(6 bottles): Belgium -€3.00; Dutch - €2.00; German -€4.00; \n"));
    }


    // Edge case tests
    @Test
    public void testGenerateReceipt_EmptyOrder() {
        when(order.getItemsPrices()).thenReturn("");
        when(order.generateReceipt()).thenReturn("");
        when(order.getDiscountRules()).thenReturn("");

        ResponseEntity<String> response = orderController.generateReceipt(order);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody());
    }

    @Test
    public void testGenerateReceipt_LargeOrder() {
        String largeData = new String(new char[10000]).replace('\0', 'A');
        when(order.getItemsPrices()).thenReturn(largeData);
        when(order.generateReceipt()).thenReturn(largeData);
        when(order.getDiscountRules()).thenReturn(largeData);

        ResponseEntity<String> response = orderController.generateReceipt(order);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(largeData + largeData + largeData, response.getBody());
    }
}
