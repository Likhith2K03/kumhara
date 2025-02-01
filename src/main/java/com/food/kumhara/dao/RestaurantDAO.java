package com.food.kumhara.dao;

import java.util.List;
import com.food.kumhara.dto.Restaurant;

public interface RestaurantDAO extends GenericDAO<Restaurant, Integer> {
    List<Restaurant> findByCuisineType(String cuisineType);
    List<Restaurant> findActiveRestaurants();
    List<Restaurant> findByRatingAbove(float rating);
}
