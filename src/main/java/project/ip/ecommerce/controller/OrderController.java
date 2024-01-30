package project.ip.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ip.ecommerce.entity.OrderTable;
import project.ip.ecommerce.service.OrderService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderTable>> getAllOrders() {
        List<OrderTable> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTable> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderTable> createOrder(@RequestBody OrderTable orderTable) {
        OrderTable createdOrder = orderService.createOrUpdateOrder(orderTable);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderTable> updateOrder(@PathVariable String id, @RequestBody OrderTable order) {
        if (orderService.getOrderById(id).isPresent()) {
            order.setId(id);
            OrderTable updatedOrder = orderService.createOrUpdateOrder(order);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order with ID " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
