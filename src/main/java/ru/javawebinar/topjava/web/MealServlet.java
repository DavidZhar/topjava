package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealListDAO;
import ru.javawebinar.topjava.dao.MealMapDao;
import ru.javawebinar.topjava.mockDB.MockDBList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealDAO mealDAO = new MealMapDao();
        log.debug("post");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime").replaceAll("T", " "), formatter);
        meal.setDateTime(dateTime);
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealId = request.getParameter("id");

        if (mealId==null||mealId.isEmpty()) mealDAO.add(meal);
        else mealDAO.update(Integer.parseInt(mealId), meal);

        List<MealTo> meals = MealsUtil.filteredByStreams(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        MealDAO dao = new MealMapDao();
        String action = request.getParameter("action");
        String forward = "";

        if (action!=null&&action.equals("delete")){
            int userId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(userId);
            forward = "/meals.jsp";
        } else if (action!=null&&action.equals("edit")){
            int userId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.get(userId);
            request.setAttribute("meal", meal);
            forward = "/edit.jsp";
        } else if (action!=null&&action.equals("add")){
            forward = "/edit.jsp";
        } else {
            forward = "/meals.jsp";
        }

        List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher(forward).forward(request, response);
//        response.sendRedirect("meals.jsp");
    }
}
