package com.example.grocery_shop3.controller;

import org.springframework.http.HttpStatus;
import com.example.grocery_shop3.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/test")
    @ResponseBody
    public String APITester() {
        return "yahoo, r u ready to order ur groceries? this is a test :)";
    }

    @PostMapping("/generate_receipt")
    @ResponseBody
    public ResponseEntity<String> generateReceipt(@RequestBody Order order) {
        if (order == null) {
            return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(order.getItemsPrices() + order.generateReceipt() + order.getDiscountRules(), HttpStatus.OK);
    }

    @GetMapping("/get_prices")
    @ResponseBody
    public String getDefaultPrices() {
        Order order = new Order();
        return order.getItemsPrices();
    }

    @GetMapping("/get_discount_rules")
    @ResponseBody
    public String getDefaultDiscounts() {
        Order order = new Order();
        return order.getDiscountRules();
    }

    // TODO: set/change discount rules values: per class
}
