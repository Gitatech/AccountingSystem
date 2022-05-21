package com.bsu.accounting.system.repository;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;

public interface Repository<T> {

    T create(int id, T entity);

    T read(int id) throws ApartmentNotFoundException;

    T update(T entity) throws ApartmentNotFoundException;

    void delete(int id);
}
