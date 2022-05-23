package com.bsu.accounting.system.dao;

import java.util.List;

public interface AbstractDAO<T, K extends Number> {

    List<T> findAll();

    T findEntityById(K id);

    boolean delete(K id);

    boolean create(T entity, K id);

    T update(T entity);

}
