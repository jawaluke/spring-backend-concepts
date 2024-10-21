package org.learn.SpringBootWorkAroundBranch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.learn.SpringBootWorkAroundBranch.enums.OrderStatus;

import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "ecom_order", schema = "system_design")
public class OrderDTO implements Serializable {

    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "products", columnDefinition = "text[]")
    private List<String> products;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "customer")
    private String username;

    @Column(name = "amount")
    private int payableAmount;
}
