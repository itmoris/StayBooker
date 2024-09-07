package com.staybooker.dao;

import java.util.List;

public interface Dao<E, K> {
    E getById(K key);

    List<E> getAll();

    void save(E element);

    void update(E element);

    void delete(E element);
}
