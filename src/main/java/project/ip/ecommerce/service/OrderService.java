package project.ip.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ip.ecommerce.entity.OrderTable;
import project.ip.ecommerce.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderTable> getAllOrders() {
        return orderRepository.findAllOrderTables();
    }

    public Optional<OrderTable> getOrderById(String id) {
        return orderRepository.findOrderTableById(id);
    }

    public OrderTable createOrUpdateOrder(OrderTable orderTable) {
        return orderRepository.save(orderTable);
    }

    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }
}
