package org.learn.SpringBootWorkAroundBranch.mapper;

import jakarta.validation.Valid;
import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.learn.SpringBootWorkAroundBranch.enums.OrderStatus;
import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Component
public class OrderMapper {

    public OrderDTO toOrderDTO(@Valid Order order) {
        OrderDTO orderDTO = OrderDTO.builder()
                .orderStatus(OrderStatus.PROGRESS)
                .payableAmount(order.getPayableAmount())
                .products(order.getProducts()).build();
        Assert.notNull(orderDTO, "error in mapping order");
        return orderDTO;
    }

}
