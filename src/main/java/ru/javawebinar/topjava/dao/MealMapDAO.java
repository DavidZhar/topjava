package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mockDB.MockDB;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapDAO implements MealDAO{
    @Override
    public void add(Meal meal) {
        meal.setId(new AtomicInteger(MockDB.getMeals().size()+1));
        MockDB.getMeals().add(meal);
    }

    @Override
    public void update(Meal meal) {
        int id = meal.getId().get();
        MockDB.getMeals().set(id-1, meal);
    }

    @Override
    public Meal get(int id) {
        return MockDB.getMeals().get(id-1);
    }

    @Override
    public List<Meal> getAll() {
        return MockDB.getMeals();
    }

    @Override
    public void delete(int id) {
        MockDB.getMeals().remove(id-1);
    }
}
