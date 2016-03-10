package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexS on 07.03.2016.
 */
public class DataHashMapMeals {
    static public Map<Integer, UserMeal> userMealMap = new HashMap<>();
    static {
        userMealMap.put(1, new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        userMealMap.put(2, new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        userMealMap.put(3, new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        userMealMap.put(4, new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        userMealMap.put(5, new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        userMealMap.put(6, new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }
}
