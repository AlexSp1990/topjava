package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.MealHashMapRepository;
import ru.javawebinar.topjava.repository.MealsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.data.DataHashMapMeals.userMealMap;
import static ru.javawebinar.topjava.util.UserMealsUtil.*;

/**
 * Created by AlexS on 05.03.2016.
 */
public class MealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("meals", getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));

        String datetimeStr = request.getParameter("datetime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(datetimeStr, formatter);

        Integer calories = Integer.parseInt(request.getParameter("calories"));

        String description = request.getParameter("description");

        Integer locId = userMealMap.keySet().stream().sorted((x, y)-> ((x > y) ? (-1) : (1))).collect(Collectors.toList()).get(0) + 1;

        UserMeal userMeal = new UserMeal(locId, dateTime, description, calories);

        MealsRepository mealsRepository = new MealHashMapRepository();
        mealsRepository.save(userMeal);
        request.setAttribute("meals", getFilteredMealsWithExceededByCycle(userMealMap.values().stream().collect(Collectors.toList()), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? ("") : (request.getParameter("action"));
        String id = request.getParameter("id");
        switch (action) {
            case "delete":
                MealsRepository mealsRepository = new MealHashMapRepository();
                mealsRepository.delete(Integer.parseInt(id));
                break;
        }
        request.setAttribute("meals", getFilteredMealsWithExceededByCycle(userMealMap.values().stream().collect(Collectors.toList()), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}
