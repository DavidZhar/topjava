package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mockDB.MockDBConcMap;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealConcMapDao implements MealDAO {
    public static AtomicInteger count = new AtomicInteger(7);

    @Override
    public void add(Meal meal) {
        meal.setId(createID());
        MockDBConcMap.getMeals().put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        MockDBConcMap.getMeals().put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return MockDBConcMap.getMeals().get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MockDBConcMap.getMeals().values());
    }

    @Override
    public void delete(int id) {
        MockDBConcMap.getMeals().remove(id);
    }

    private int createID() {
        return count.incrementAndGet();
    }
}
