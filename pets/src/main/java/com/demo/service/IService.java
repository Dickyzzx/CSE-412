package com.demo.service;

public interface IService<T> {

    Boolean saveOne(T entity);
}
