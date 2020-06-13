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

    public List<Meal> getAll() {
        return new ArrayList<>(repository.getAll());
    }

    public Meal get(int id) {
        return Objects.requireNonNull(repository.get(id));
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) {
        if (!repository.delete(id)) throw new NullPointerException();
    }

    public void update(Meal meal) {
        if (repository.save(meal) == null) throw new NullPointerException();
    }
}