package com.bonc.dao;

import java.util.List;

public interface BaseDao<T> {
    void save(T entity);
    void update(T entity);
    void delete(Long id);
    T getById(Long id);
    List<T> findAll();
}
