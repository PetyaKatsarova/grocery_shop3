package com.example.grocery.shop3.controller;

import org.springframework.http.HttpStatus;
import com.example.grocery.shop3.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grocery-shop")
public class ReceiptsController {

    @GetMapping("/test")
    @ResponseBody
    public String APITester() {
        return "yahoo, r u ready to order ur groceries? this is a test :)";
    }

    @PostMapping("/receipts/current")
    @ResponseBody
    public ResponseEntity<String> generateReceipt(@RequestBody Order order) {
        if (order == null) {
            return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
        }
        System.out.println("order: " + order);
        return new ResponseEntity<>(order.generateReceipt(), HttpStatus.OK);
    }
}
