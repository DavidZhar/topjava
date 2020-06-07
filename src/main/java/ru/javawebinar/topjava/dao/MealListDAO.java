package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mockDB.MockDBList;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealListDAO implements MealDAO{
    @Override
    public void add(Meal meal) {
        meal.setId(MockDBList.getMeals().size()+1);
        MockDBList.getMeals().add(meal);
    }

    @Override
    public void update(int id, Meal meal) {
        MockDBList.getMeals().set(id-1, meal);
    }

    @Override
    public Meal get(int id) {
        return MockDBList.getMeals().get(id-1);
    }

    @Override
    public List<Meal> getAll() {
        return MockDBList.getMeals();
    }

    @Override
    public void delete(int id) {
        MockDBList.getMeals().remove(id-1);
    }
}
