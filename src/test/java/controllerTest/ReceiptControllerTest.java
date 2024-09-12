package controllerTest;

import com.example.grocery.shop3.GroceryShop3Application;
import com.example.grocery.shop3.controller.ReceiptController;
import com.example.grocery.shop3.model.Beer;
import com.example.grocery.shop3.model.Bread;
import com.example.grocery.shop3.model.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReceiptController.class)
@ContextConfiguration(classes = GroceryShop3Application.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGenerateReceiptSuccess() throws Exception {
        Receipt receipt = new Receipt();
        receipt.addItem(new Beer());
        receipt.addItem(new Bread());

        // Convert Receipt object to JSON
        String receiptJson = objectMapper.writeValueAsString(receipt);

        mockMvc.perform(post("/grocery-shop/receipts/current")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptJson))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(content().string(receipt.generateReceipt()));
    }

    @Test
    public void testGenerateReceiptWithNullReceipt() throws Exception {
        // Perform a POST request with a null Receipt object
        mockMvc.perform(post("/grocery-shop/receipts/current")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())  // Expect HTTP 400 BAD REQUEST
                .andExpect(content().string("Receipt not found"));
    }

    // TODO: json validation schema implementation in the receiptController
//    @Test
//    public void testGenerateReceiptWithMissingFields() throws Exception {
//        // Create an invalid JSON with missing fields, such as missing "quantity" or wrong field "age"
//        String invalidJson = "{ \"items\": [ { \"name\": \"bread\" }, { \"name\": \"vegetable\", \"price\": 1.00, \"weight_for_price\": 100, \"unit\": \"g\" } ] }";
//
//        // Perform a POST request with the invalid JSON
//        mockMvc.perform(post("/grocery-shop/receipts/current")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(invalidJson))
//                .andExpect(status().isBadRequest())  // Expect HTTP 400 BAD REQUEST
//                .andExpect(content().string("Invalid item data"));  // Adjust the message based on your validation logic
//    }

//    @Test
//    public void testGenerateReceiptWithInvalidFields() throws Exception {
//        // Create a JSON with invalid fields, such as a negative "price" or an invalid "quantity"
//        String invalidJson = "{ \"items\": [ { \"name\": \"bread\", \"price\": -1.00, \"quantity\": -5 }, { \"name\": \"vegetable\", \"price\": 1.00, \"weight_for_price\": 100, \"unit\": \"g\" } ] }";
//
//        // Perform a POST request with the invalid JSON
//        mockMvc.perform(post("/grocery-shop/receipts/current")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(invalidJson))
//                .andExpect(status().isBadRequest())  // Expect HTTP 400 BAD REQUEST
//                .andExpect(content().string("Invalid item data"));  // Adjust the message based on your validation logic
//    }
}

