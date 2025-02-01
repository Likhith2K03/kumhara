package com.food.kumhara.dao;

import java.util.List;
import com.food.kumhara.dto.Order;

public interface OrderDAO extends GenericDAO<Order, Integer> {
    List<Order> findByUserId(int userId);
    List<Order> findByRestaurantId(int restaurantId);
    List<Order> findOrdersByStatus(String status);
   
    /**
     * Saves the order and returns the generated order ID
     * @param order Order to be saved
     * @return The saved order with generated ID
     */
    Order saveAndGetId(Order order);
}
