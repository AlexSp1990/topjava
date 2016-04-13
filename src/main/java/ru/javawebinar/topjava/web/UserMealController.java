package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Date;

import static ru.javawebinar.topjava.util.UserMealsUtil.getFilteredWithExceeded;

/**
 * Created by AlexS on 10.04.2016.
 */
@Controller
@RequestMapping(value = "/meals")
public class UserMealController {
    @Autowired
    private UserMealService service;

    @RequestMapping( method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String mealDelete(@PathVariable("id") int id) {
        //model.addAttribute("meal", UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), LoggedUser.getCaloriesPerDay()));
        service.delete(id, LoggedUser.id());
        return "redirect:../../meals";
    }

    @RequestMapping(value = "/getmeal", method = RequestMethod.GET)
    public String mealGet(Model model, @RequestParam("id") int id) {
        model.addAttribute("meal", service.get(id, LoggedUser.id()));
        //service.delete(id, LoggedUser.id());
        return "mealEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String mealEdit(@RequestParam("id") int id, @RequestParam("calories") int calories,
                           @RequestParam("description") String description) {
        //model.addAttribute("meal", service.get(id, LoggedUser.id()));
        UserMeal userMeal = service.get(id, LoggedUser.id());
        userMeal.setCalories(calories);
        userMeal.setDescription(description);
        service.save(userMeal, LoggedUser.id());
        return "redirect:../meals";
    }

}
