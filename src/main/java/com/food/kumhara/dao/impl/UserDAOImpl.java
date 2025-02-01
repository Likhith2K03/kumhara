package com.food.kumhara.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.food.kumhara.dao.UserDAO;
import com.food.kumhara.dto.User;
import com.food.kumhara.util.ConnectionFactory;

public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {


	public UserDAOImpl() {
		super(User.class, ConnectionFactory.getSessionFactory());
	}

	@Override
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return findByUsername(username) == null;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return findByEmail(email) == null;
    }
}
