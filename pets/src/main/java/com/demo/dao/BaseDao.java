package com.demo.dao;

public interface BaseDao<T> {

    Boolean insertOne(T entity);

}
