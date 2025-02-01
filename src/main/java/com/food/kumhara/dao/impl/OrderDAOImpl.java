package com.food.kumhara.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.food.kumhara.dao.OrderDAO;
import com.food.kumhara.dto.Order;
import com.food.kumhara.util.ConnectionFactory;

public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderDAO {

    public OrderDAOImpl() {
		super(Order.class, ConnectionFactory.getSessionFactory());
	}

	@Override
    public List<Order> findByUserId(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("FROM Order WHERE user_id = :userId", Order.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    @Override
    public List<Order> findByRestaurantId(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("FROM Order WHERE restaurant_id = :restaurantId", Order.class);
            query.setParameter("restaurantId", restaurantId);
            return query.list();
        }
    }

    @Override
    public List<Order> findOrdersByStatus(String status) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("FROM Order WHERE status = :status", Order.class);
            query.setParameter("status", status);
            return query.list();
        }
    }

    @Override
    public Order saveAndGetId(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            session.flush(); // Force the persistence to get the generated ID
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
