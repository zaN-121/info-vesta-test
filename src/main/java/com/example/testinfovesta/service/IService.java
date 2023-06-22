package com.example.testinfovesta.service;

import java.util.List;

public interface IService<T,U> {
    public T add(U movieRequest);
    public void deleteById(Integer id);
    public T updateById(U movieRequest, Integer id);
    public List<T> getAll();
    public T getById(Integer id);
}
