package com.food.kumhara.dao;

import java.util.List;
import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {
    /**
     * Save or persist an entity in the database.
     * 
     * @param entity the entity to be saved.
     * @return the saved entity.
     */
    T save(T entity);

    /**
     * Retrieve an entity from the database using its primary key.
     * 
     * @param id the primary key of the entity.
     * @return the entity, or null if not found.
     */
    T findById(ID id);

    /**
     * Retrieve all entities of a specific type from the database.
     * 
     * @return a list of all entities.
     */
    List<T> findAll();

    /**
     * Update an existing entity in the database.
     * 
     * @param entity the entity to be updated.
     * @return the updated entity.
     */
    T update(T entity);

    /**
     * Delete an entity from the database.
     * 
     * @param entity the entity to be deleted.
     */
    void delete(T entity);
}

