package com.food.kumhara;

import java.util.List;

import com.food.kumhara.dao.impl.MenuDAOImpl;
import com.food.kumhara.dto.Menu;

public class App {
    public static void main(String[] args) {
    	MenuDAOImpl restaurantDAOImpl = new MenuDAOImpl();
    	List<Menu> menus = restaurantDAOImpl.findByRestaurantId(3);
    	for (Menu menu : menus) {
			System.out.println(menu);
		}
    }
}

