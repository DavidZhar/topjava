package ru.javawebinar.topjava.mockDB;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockDBConcMap {
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    static {
        meals.put(1, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 1));
        meals.put(2, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, 2));
        meals.put(3, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, 3));
        meals.put(4, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, 4));
        meals.put(5, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, 5));
        meals.put(6, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, 6));
        meals.put(7, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, 7));
    }

    public static Map<Integer, Meal> getMeals() {
        return meals;
    }

    public static void setMeals(Map<Integer, Meal> meals) {
        MockDBConcMap.meals = meals;
    }
}
