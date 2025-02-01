package com.food.kumhara.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.food.kumhara.dao.OrderItemDAO;
import com.food.kumhara.dto.OrderItem;
import com.food.kumhara.util.ConnectionFactory;

public class OrderItemDAOImpl extends GenericDAOImpl<OrderItem, Integer> implements OrderItemDAO {

    public OrderItemDAOImpl() {
		super(OrderItem.class, ConnectionFactory.getSessionFactory());
	}

	@Override
    public List<OrderItem> findByOrderId(int orderId) {
        try (Session session = sessionFactory.openSession()) {
            Query<OrderItem> query = session.createQuery("FROM OrderItem WHERE order_id = :orderId", OrderItem.class);
            query.setParameter("orderId", orderId);
            return query.list();
        }
    }

    @Override
    public double calculateTotalOrderPrice(int orderId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery("SELECT SUM(total_price) FROM OrderItem WHERE order_id = :orderId", Double.class);
            query.setParameter("orderId", orderId);
            Double result = query.uniqueResult();
            return result != null ? result : 0.0;
        }
    }
}
