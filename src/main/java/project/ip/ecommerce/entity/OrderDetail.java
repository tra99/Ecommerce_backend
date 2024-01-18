// package project.ip.ecommerce.entity;


// import jakarta.persistence.*;

// @Entity
// public class OrderDetail {
//     @Id
//     private String id;

//     @ManyToOne
//     @JoinColumn(name = "order_id")
//     private Order order;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     private Product product;

//     private int quantity;
//     private float price;

//     // Constructors, getters, setters, and other annotations as needed

//     // Constructor
//     public OrderDetail() {
//         this.id = UUID.randomUUID().toString();
//     }

//     // Getters and Setters
//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public Order getOrder() {
//         return order;
//     }

//     public void setOrder(Order order) {
//         this.order = order;
//     }

//     public Product getProduct() {
//         return product;
//     }

//     public void setProduct(Product product) {
//         this.product = product;
//     }

//     public int getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(int quantity) {
//         this.quantity = quantity;
//     }

//     public float getPrice() {
//         return price;
//     }

//     public void setPrice(float price) {
//         this.price = price;
//     }
// }

