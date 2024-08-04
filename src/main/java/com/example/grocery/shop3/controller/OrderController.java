package com.example.grocery.shop3.controller;

import org.springframework.http.HttpStatus;
import com.example.grocery.shop3.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grocery-shop")
public class OrderController {

    @GetMapping("/test")
    @ResponseBody
    public String APITester() {
        return "yahoo, r u ready to order ur groceries? this is a test :)";
    }

    @PostMapping("/generate-receipt")
    @ResponseBody
    public ResponseEntity<String> generateReceipt(@RequestBody Order order) {
        if (order == null) {
            return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(order.generateReceipt(), HttpStatus.OK);
    }

    @GetMapping("/get-prices")
    @ResponseBody
    public String getDefaultPrices() {
        Order order = new Order();
        return order.getItemsPrices();
    }

    @GetMapping("/get-discount-rules")
    @ResponseBody
    public String getDefaultDiscounts() {
        Order order = new Order();
        return order.getDiscountRules();
    }

    // TODO: set/change discount rules values: per class
}
