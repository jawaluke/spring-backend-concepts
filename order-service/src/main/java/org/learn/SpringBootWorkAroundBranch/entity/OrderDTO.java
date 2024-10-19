package org.learn.SpringBootWorkAroundBranch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learn.SpringBootWorkAroundBranch.enums.OrderStatus;

import java.util.List;

@Entity
@Builder
@Table(name = "order", schema = "ecom")
public class OrderDTO {

    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "products")
    private List<String> products;

    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "user")
    private String username;

    @Column(name = "amount")
    private int payableAmount;
}
