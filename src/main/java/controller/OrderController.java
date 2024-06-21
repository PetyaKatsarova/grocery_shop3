package controller;

import com.grocerystore.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private Order order = new Order();

    @PostMapping("/add")
    public void addItem(@RequestBody Item item) {
        order.addItem(item);
    }

    @GetMapping("/total")
    public double getTotal() {
        return order.calculateTotal();
    }

    @GetMapping("/receipt")
    public String getReceipt() {
        return order.generateReceipt();
    }
}
