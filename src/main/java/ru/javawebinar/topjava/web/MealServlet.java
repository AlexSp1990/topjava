package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static ru.javawebinar.topjava.util.UserMealsUtil.*;

/**
 * Created by AlexS on 05.03.2016.
 */
public class MealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("meals", getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}
