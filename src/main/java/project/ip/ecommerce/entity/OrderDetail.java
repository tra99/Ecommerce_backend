package project.ip.ecommerce.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private String id;

    public OrderDetail(){
        this.id=UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    private int quantity;
    private float price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="product_table_id")
    private Product product;

    @Column(name = "product_id")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

}
