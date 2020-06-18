package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MealService {

    @Autowired
    private MealRepository repository;

    public List<Meal> getAll(int userId) {
        return new ArrayList<>(repository.getAll(userId));
    }

    public Meal get(int id, int userId) {
        return Objects.requireNonNull(repository.get(id, userId));
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        if (!repository.delete(id, userId)) throw new NullPointerException();
    }

    public void update(Meal meal, int userId) {
        if (repository.save(meal, userId) == null) throw new NullPointerException();
    }
}