package com.epam.training.dao;

import java.util.List;

public interface GenericDAO<T> {
    void create(T entity);

    void read(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
