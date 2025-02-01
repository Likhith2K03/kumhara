package com.food.kumhara.dao;

import java.util.List;
import com.food.kumhara.dto.OrderItem;

public interface OrderItemDAO extends GenericDAO<OrderItem, Integer> {
    List<OrderItem> findByOrderId(int orderId);
    double calculateTotalOrderPrice(int orderId);
}
