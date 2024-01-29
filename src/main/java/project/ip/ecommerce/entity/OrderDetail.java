// OrderDetail class
package project.ip.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    private String id;
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

    @ManyToOne
    @JoinColumn(name="product_table_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

}
