package com.food.kumhara.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.food.kumhara.dao.RestaurantDAO;
import com.food.kumhara.dto.Restaurant;
import com.food.kumhara.util.ConnectionFactory;

public class RestaurantDAOImpl extends GenericDAOImpl<Restaurant, Integer> implements RestaurantDAO {

    public RestaurantDAOImpl() {
		super(Restaurant.class, ConnectionFactory.getSessionFactory());
	}

	@Override
    public List<Restaurant> findByCuisineType(String cuisineType) {
        try (Session session = sessionFactory.openSession()) {
            Query<Restaurant> query = session.createQuery("FROM Restaurant r WHERE r.cuisineType = :cuisineType", Restaurant.class);
            query.setParameter("cuisineType", cuisineType);
            return query.list();
        }
    }

    @Override
    public List<Restaurant> findActiveRestaurants() {
        try (Session session = sessionFactory.openSession()) {
            Query<Restaurant> query = session.createQuery("FROM Restaurant r WHERE r.isActive = true", Restaurant.class);
            return query.list();
        }
    }

    @Override
    public List<Restaurant> findByRatingAbove(float rating) {
        try (Session session = sessionFactory.openSession()) {
            Query<Restaurant> query = session.createQuery("FROM Restaurant r WHERE r.rating >= :rating", Restaurant.class);
            query.setParameter("rating", rating);
            return query.list();
        }
    }
}
