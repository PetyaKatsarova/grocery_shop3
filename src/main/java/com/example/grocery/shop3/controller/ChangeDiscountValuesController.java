package com.example.grocery.shop3.controller;

import com.example.grocery.shop3.model.DiscountStrategyBeer;
import com.example.grocery.shop3.model.DiscountStrategyBread;
import com.example.grocery.shop3.model.DiscountStrategyVegetable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grocery-shop")
public class ChangeDiscountValuesController {

    @PostMapping("change-discount/beer")
    @ResponseBody
    public ResponseEntity<String> setBeerDiscount(@RequestBody DiscountStrategyBeer discountStrategyBeer) {
        if (discountStrategyBeer == null) {
            return new ResponseEntity<>("Enter valid values", HttpStatus.BAD_REQUEST);
        }
        System.out.println("beer controller change disc: " + discountStrategyBeer.toString());
        return new ResponseEntity<>(discountStrategyBeer.toString(), HttpStatus.OK);
    }

    @PostMapping("change-discount/bread")
    @ResponseBody
    public ResponseEntity<String> setBreadDiscount(@RequestBody DiscountStrategyBread discountStrategyBread) {
        if (discountStrategyBread == null) {
            return new ResponseEntity<>("Enter valid values", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(discountStrategyBread.toString(), HttpStatus.OK);
    }

    @PostMapping("change-discount/vegetable")
    @ResponseBody
    public ResponseEntity<String> setVegetableDiscount(@RequestBody DiscountStrategyVegetable discountStrategyVegetable) {
        if (discountStrategyVegetable == null) {
            return new ResponseEntity<>("Enter valid values", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(discountStrategyVegetable.toString(), HttpStatus.OK);
    }
}
