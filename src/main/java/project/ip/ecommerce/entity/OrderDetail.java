// OrderDetail class
package project.ip.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    private String id;

    // Other fields...

    @ManyToOne
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

    // Other getters and setters...
}
