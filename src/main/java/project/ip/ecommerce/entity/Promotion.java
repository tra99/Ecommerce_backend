package project.ip.ecommerce.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.GenericGenerator; 

@Entity
@Table(name = "promotion")
@Data
public class Promotion {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;
    private String description;
    private float discount;
   
    public Promotion(String id, String name, String description, float discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discount = discount;
    }

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Promotion() {
        this.startDate = LocalDateTime.now();
    }
}
