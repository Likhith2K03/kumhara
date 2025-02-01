package com.food.kumhara.dao.impl;

import java.util.List;
import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.food.kumhara.dao.GenericDAO;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected final SessionFactory sessionFactory;
    private final Class<T> entityType;

    /**
     * Constructor to initialize the entity class and the SessionFactory.
     * 
     * @param entityType the entity class type.          
     * @param sessionFactory the Hibernate SessionFactory.
     */
    public GenericDAOImpl(Class<T> entityType, SessionFactory sessionFactory) {
        this.entityType = entityType;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T save(T entity) {
        try (var session = sessionFactory.openSession()) {
        	var transaction = session.beginTransaction();
            session.persist(entity);  // Save the entity
            session.flush();  // Force the persistence context to be synchronized with the database
            transaction.commit();
            return entity;  // Return the saved entity with generated ID
        }
    }

    @Override
    public T findById(ID id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(entityType, id);
        }
    }

    @Override
    public List<T> findAll() {
        try (var session = sessionFactory.openSession()) {
        	var query = session.createQuery("FROM " + entityType.getName(), entityType);
            return query.list();  // Return all entities of the type
        }
    }

    @Override
    public T update(T entity) {
        try (var session = sessionFactory.openSession()) {
        	var transaction = session.beginTransaction();
            session.merge(entity);  // Update the entity
            transaction.commit();
            return entity;  // Return the updated entity
        }
    }

    @Override
    public void delete(T entity) {
        try (var session = sessionFactory.openSession()) {
        	var transaction = session.beginTransaction();
            session.remove(entity);  // Delete the entity
            transaction.commit();
        }
    }
}
