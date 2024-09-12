package com.example.grocery.shop3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.grocery.shop3.model.Receipt;

@RestController
@RequestMapping("/grocery-shop")
public class ReceiptController {

    @PostMapping("/receipts/current")
    public ResponseEntity<String> generateReceipt(@RequestBody Receipt receipt) {
        try {
            if (receipt == null || receipt.getItems().isEmpty()) {
                return new ResponseEntity<>("Receipt not found", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(receipt.generateReceipt(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
