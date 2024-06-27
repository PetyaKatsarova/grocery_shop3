package com.example.grocery_shop3.controller;

import com.example.grocery_shop3.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/get_prices")
    @ResponseBody
    public String getItemsPrices() {

        Order order = new Order();
        return order.getItemsPrices();
    }

    @GetMapping("/test")
    @ResponseBody
    public String GameTester() {
        return "yahoo, r u ready to order ur groceries? this is a test :)";
    }

    @GetMapping("/discount_rules")
    @ResponseBody
    public String GetDiscountRules() {
        return  "● Breads always have discounts like “buy 1 take 2”, or “buy 1\n" +
                "take 3”. If the bread is one day old or newer, it has no\n" +
                "discounts. If it's 3 days old it has the “buy 1 take 2” discount,\n" +
                "and if it's 6 days old, it's “pay 1 take 3”. Breads older than 6\n" +
                "days cannot be added to orders." +
                "BREAD: " +
                "VEGETABLES:" +
                "● If you buy between 0g and 100g (always in the same\n" +
                "order), you have 5% of discount (applied to all vegetable\n" +
                "items), 7% between 100g and 500g, and 10% of fixed discount\n" +
                "if all vegetables weight more than 500g." +
                "BEER:" +
                "● Beers have only discounts if bought in packs containing 6\n" +
                "beers. The discount rules are fixed per pack:\n" +
                "● € 3,00 for each Belgium beer pack\n" +
                "● € 2,00 for each Dutch beer pack\n" +
                "● € 4,00 for each German beer pack\n" +
                "Single bottles/cans of beer can always be added to the order,\n" +
                "but in that case there is no discount. Buying 6 separate\n" +
                "bottles of the same beer is the same as buying one pack of\n" +
                "the same beer.";
    }

    @PostMapping("/generate_receipt")
    @ResponseBody
    public String generateReceipt(@RequestBody Order order) {
        return order.generateReceipt();
    }

}
