package org.learn.SpringBootWorkAroundBranch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private long orderId;
    private List<String> products;
    private String username;
    private int payableAmount;
}
