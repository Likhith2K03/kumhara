package com.food.kumhara.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.food.kumhara.dao.MenuDAO;
import com.food.kumhara.dto.Menu;
import com.food.kumhara.util.ConnectionFactory;

public class MenuDAOImpl extends GenericDAOImpl<Menu, Integer> implements MenuDAO {

    public MenuDAOImpl() {
		super(Menu.class, ConnectionFactory.getSessionFactory());
	}

	@Override
    public List<Menu> findByRestaurantId(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
        	Query<Menu> query = session.createQuery("FROM Menu m WHERE m.restaurant.id = :restaurantId", Menu.class);
        	query.setParameter("restaurantId", restaurantId);
            return query.list();
        }
    }

    @Override
    public List<Menu> findAvailableItems(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Menu> query = session.createQuery("FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.isAvailable = true", Menu.class);
            query.setParameter("restaurantId", restaurantId);
            return query.list();
        }
    }

    @Override
    public List<Menu> findItemsByPriceRange(double minPrice, double maxPrice) {
        try (Session session = sessionFactory.openSession()) {
            Query<Menu> query = session.createQuery("FROM Menu m WHERE m.price BETWEEN :minPrice AND :maxPrice", Menu.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        }
    }
}
