package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public List<UserMealWithExceed> getAll() {
        LOG.info("getAll");
        int userId = LoggedUser.id();
        return UserMealsUtil.getFilteredWithExceeded(service.getAll(userId).stream().collect(Collectors.toList()), LocalTime.of(0, 0), LocalTime.of(23, 59), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        int userId = LoggedUser.id();
        return service.get(id, userId);
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        //userMeal.setId(null);
        int userId = LoggedUser.id();
        return service.save(userMeal, userId);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        int userId = LoggedUser.id();
        service.delete(id, userId);
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }
}
