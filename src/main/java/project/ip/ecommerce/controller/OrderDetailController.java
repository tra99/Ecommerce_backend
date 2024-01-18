// package project.ip.ecommerce.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import project.ip.ecommerce.entity.OrderDetail;
// import project.ip.ecommerce.service.OrderDetailService;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/order-details")
// public class OrderDetailController {

//     private final OrderDetailService orderDetailService;

//     public OrderDetailController(OrderDetailService orderDetailService) {
//         this.orderDetailService = orderDetailService;
//     }

//     @GetMapping
//     public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
//         List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
//         return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable String id) {
//         return orderDetailService.getOrderDetailById(id)
//                 .map(orderDetail -> new ResponseEntity<>(orderDetail, HttpStatus.OK))
//                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @PostMapping
//     public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
//         OrderDetail createdOrderDetail = orderDetailService.createOrUpdateOrderDetail(orderDetail);
//         return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable String id, @RequestBody OrderDetail orderDetail) {
//         if (orderDetailService.getOrderDetailById(id).isPresent()) {
//             orderDetail.setId(id);
//             OrderDetail updatedOrderDetail = orderDetailService.createOrUpdateOrderDetail(orderDetail);
//             return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deleteOrderDetail(@PathVariable String id) {
//         orderDetailService.deleteOrderDetailById(id);
//         return new ResponseEntity<>("OrderDetail with ID " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
//     }
// }
