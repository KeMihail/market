package com.epam.service;

import java.util.List;

public interface DefaultService<T> {

    T getById(Integer id);

    List<T> getAll();

    T save(T t);

    void delete (T t);
}
