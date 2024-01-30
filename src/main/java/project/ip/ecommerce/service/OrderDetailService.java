package project.ip.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ip.ecommerce.entity.OrderDetail;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository,ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService=productService;
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(String id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetail createOrUpdateOrderDetail(OrderDetail orderDetail,String productId) {
        Product product=productService.getProductById(productId).orElse(null);
        orderDetail.setProduct(product);
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetailById(String id) {
        orderDetailRepository.deleteById(id);
    }
}
