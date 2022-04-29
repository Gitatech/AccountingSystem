package com.prokopchyk.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    void save(T object);
    void update(T object,T objectNew );
    void delete(T object);

}
