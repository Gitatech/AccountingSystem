package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;

public interface Dao<T> {

    T create(int id, T entity);

    T read(int id) throws ApartmentNotFoundException;

    T update(T entity) throws ApartmentNotFoundException;

    void delete(int id);
}
