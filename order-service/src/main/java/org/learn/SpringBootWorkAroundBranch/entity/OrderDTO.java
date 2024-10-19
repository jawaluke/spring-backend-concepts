package org.learn.SpringBootWorkAroundBranch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "products", columnDefinition = "jsonb")
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<String> products;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "user")
    private String username;

    @Column(name = "amount")
    private int payableAmount;
}
