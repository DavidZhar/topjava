package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@RequestMapping("/meals")
@Controller
public class JspMealController {

    @Autowired
    private MealRestController mealController;

    @GetMapping()
    public String getFilteredMeals(Model model, HttpServletRequest request, @RequestParam(value = "action", required = false) String action) {
        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                mealController.delete(id);
                return "redirect:meals";
            }
            case "create", "update" -> {
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                model.addAttribute("meal", meal);
                return "mealForm";
            }
            case "filter" -> {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
                model.addAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
                return "meals";
            }
            default -> {
                model.addAttribute("meals", mealController.getAll());
                return "meals";
            }
        }
    }

    @PostMapping()
    public String changeMeals(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            mealController.create(meal);
        } else {
            mealController.update(meal, getId(request));
        }
        return "redirect:meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

//1.3 Перенести функциональность MealServlet в JspMealController контроллер (по аналогии с RootController).
// MealRestController у нас останется, с ним будем работать позже.
//1.3.1 разнести запросы на update/delete/.. по разным методам (попробуйте вообще без action=).
// Можно по аналогии с RootController#setUser принимать HttpServletRequest request
// (аннотации на параметры и адаптеры для LocalDate/Time мы введем позже).