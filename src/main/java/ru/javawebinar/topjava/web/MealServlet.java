package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealMapDAO;
import ru.javawebinar.topjava.mockDB.MockDB;
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
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealDAO mealDAO = new MealMapDAO();
        log.debug("post");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        MealDAO dao = new MealMapDAO();
        String action = request.getParameter("action");
        if (action!=null&&action.equals("delete")){
            int userId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(userId);
        } else if (action!=null&&action.equals("edit")){
            int userId = Integer.parseInt(request.getParameter("mealId"));
            dao.get(userId);
        }



        List<MealTo> meals = MealsUtil.filteredByStreams(MockDB.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", meals);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
    }
}
