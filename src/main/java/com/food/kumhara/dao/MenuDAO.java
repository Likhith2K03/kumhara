package com.food.kumhara.dao;

import java.util.List;
import com.food.kumhara.dto.Menu;

public interface MenuDAO extends GenericDAO<Menu, Integer> {
    /**
     * @param restaurantId
     * @return
     */
    List<Menu> findByRestaurantId(int restaurantId);
    /**
     * @param restaurantId
     * @return
     */
    List<Menu> findAvailableItems(int restaurantId);
    /**
     * @param minPrice
     * @param maxPrice
     * @return
     */
    List<Menu> findItemsByPriceRange(double minPrice, double maxPrice);
}
