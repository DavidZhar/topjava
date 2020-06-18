package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;


@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        int userId = authUserId();
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        int userId = authUserId();
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        int userId = authUserId();
        meal.setUserID(userId);
        return service.create(meal, userId);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        int userId = authUserId();
        service.delete(id, userId);
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal, meal.getId());
        int userId = authUserId();
        meal.setUserID(userId);
        service.update(meal, userId);
    }


}