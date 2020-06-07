package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DAO<T> {
    void add(T t);
    void update(int id, T t);
    T get(int id);
    List<T> getAll();
    void delete(int id);
}
