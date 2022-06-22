package com.epam;

import java.util.List;

public interface DefaultDao<T> {
    T getById(Integer id);

    List<T> getAll();

    T create(T t);

    T update(T t);

    void delete (T t);
}
