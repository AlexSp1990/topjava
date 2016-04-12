package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;

import static ru.javawebinar.topjava.util.UserMealsUtil.getFilteredWithExceeded;

/**
 * Created by AlexS on 10.04.2016.
 */
@Controller
public class UserMealController {
    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }

//    @RequestMapping(value = "/meals", method = RequestMethod.POST)
//    public String setMeal(HttpServletRequest request) {
//        service.save();
//        return "redirect:meals";
//    }

}
