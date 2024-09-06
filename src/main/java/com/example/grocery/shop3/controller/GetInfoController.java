package com.example.grocery.shop3.controller;

import com.example.grocery.shop3.model.Receipt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grocery-shop")
public class GetInfoController {
    @GetMapping("/prices")
    @ResponseBody
    public String getDefaultPrices() {
        Receipt order = new Receipt();
        return order.getItemsPrices();
    }

    @GetMapping("/discount-rules")
    @ResponseBody
    public String getDefaultDiscounts() {
        Receipt order = new Receipt();
        return order.getDiscountRules();
    }
}
