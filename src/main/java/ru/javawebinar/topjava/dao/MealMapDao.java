package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mockDB.MockDBMap;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapDao implements MealDAO {
    public static AtomicInteger count = new AtomicInteger(7);

    @Override
    public void add(Meal meal) {
        meal.setId(createID());
        MockDBMap.getMeals().put(meal.getId(), meal);
    }

    @Override
    public void update(int id, Meal meal) {
        MockDBMap.getMeals().put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return MockDBMap.getMeals().get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MockDBMap.getMeals().values());
    }

    @Override
    public void delete(int id) {
        MockDBMap.getMeals().remove(id);
    }

    private int createID(){
        return count.incrementAndGet();
    }
}
