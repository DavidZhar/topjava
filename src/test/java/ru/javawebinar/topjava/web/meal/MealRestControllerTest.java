package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.TestMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.TestUtil.readFromJson;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    MealService mealService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingFieldsComparator(MealTo.class, "user", "excess")
                        .contentJson(MealsUtil.getTos(MEALS, authUserCaloriesPerDay())));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingFieldsComparator(Meal.class, "user", "excess")
                        .contentJson(List.of(MEAL1).get(0)));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID, authUserId()));
    }

    @Test
    void update() throws Exception {
        Meal updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        TestMatcher.usingFieldsComparator(Meal.class, "user", "excess")
                .assertMatch(mealService.get(MEAL1_ID, authUserId()), updated);
    }

    @Test
    void createMeal() throws Exception {
        Meal newMeal = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)))
                .andExpect(status().isCreated());

        MealTo created = readFromJson(action, MealTo.class);
        int newId = created.getId();
        newMeal.setId(newId);
        TestMatcher.usingFieldsComparator(MealTo.class, "user", "excess")
                .assertMatch(created, MealsUtil.getTos(List.of(newMeal), Integer.MAX_VALUE).get(0));
        TestMatcher.usingFieldsComparator(Meal.class, "user", "excess")
                .assertMatch(mealService.get(newId, authUserId()), newMeal);
    }

    @Test
    void getMealsBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "between/datetime?startDate=2011-12-03T10:15:30"
                + "&startTime=2011-12-03T10:15:30&endDate=2021-12-03T10:15:30&endTime=2021-12-03T22:15:30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingFieldsComparator(MealTo.class, "user", "excess")
                        .contentJson(MealsUtil.getFilteredTos(mealService.getBetweenInclusive(LocalDateTime.parse("2011-12-03T10:15:30").toLocalDate(),
                                LocalDateTime.parse("2021-12-03T10:15:30").toLocalDate(), authUserId()), authUserCaloriesPerDay(),
                                LocalDateTime.parse("2011-12-03T10:15:30").toLocalTime(), LocalDateTime.parse("2021-12-03T22:15:30").toLocalTime())));
    }

    @Test
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "between?startDate=2011-12-03"
                + "&startTime=10:15:30&endDate=2021-12-03&endTime=22:15:30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingFieldsComparator(MealTo.class, "user", "excess")
                        .contentJson(MealsUtil.getFilteredTos(mealService.getBetweenInclusive(LocalDateTime.parse("2011-12-03T10:15:30").toLocalDate(),
                                LocalDateTime.parse("2021-12-03T10:15:30").toLocalDate(), authUserId()), authUserCaloriesPerDay(),
                                LocalDateTime.parse("2011-12-03T10:15:30").toLocalTime(), LocalDateTime.parse("2021-12-03T22:15:30").toLocalTime())));

        perform(MockMvcRequestBuilders.get(REST_URL + "between?startDate="
                + "&startTime=10:15:30&endDate=2021-12-03&endTime="))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestMatcher.usingFieldsComparator(MealTo.class, "user", "excess")
                        .contentJson(MealsUtil.getFilteredTos(mealService.getBetweenInclusive(null,
                                LocalDateTime.parse("2021-12-03T10:15:30").toLocalDate(), authUserId()), authUserCaloriesPerDay(),
                                LocalDateTime.parse("2011-12-03T10:15:30").toLocalTime(), null)));
    }
}