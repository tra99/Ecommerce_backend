package project.ip.ecommerce.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
@Data
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private float total = 0.0f;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Temporal(TemporalType.DATE)
    private Date date;


    // Constructor for initializing collections
    public OrderTable() {
        this.orderDetails = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.date = new Date();
    }

    @OneToMany(mappedBy = "orderTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrderTable(this);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setOrderTable(null);
    }
}
