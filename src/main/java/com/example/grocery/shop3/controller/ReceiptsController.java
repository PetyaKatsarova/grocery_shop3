package com.example.grocery.shop3.controller;

import com.example.grocery.shop3.model.Receipt;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> generateReceipt(@RequestBody Receipt receipt) {
        if (receipt == null) {
            return new ResponseEntity<>("Receipt not tound", HttpStatus.BAD_REQUEST);
        }
        System.out.println("receipt: " + receipt);
        return new ResponseEntity<>(receipt.generateReceipt(), HttpStatus.OK);
    }
}
