package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int USER_MEAL_ID_1 = 100002;
    public static final int USER_MEAL_ID_2 = 100003;
    public static final int USER_MEAL_ID_3 = 100004;

    public static final UserMeal USER_MEAL_1 = new UserMeal(USER_MEAL_ID_1, LocalDateTime.of(2015, 5, 30, 10, 0), "Завтрак", 500);
    public static final UserMeal USER_MEAL_2 = new UserMeal(USER_MEAL_ID_2, LocalDateTime.of(2015, 5, 30, 14, 0), "Обед", 1100);
    public static final UserMeal USER_MEAL_3 = new UserMeal(USER_MEAL_ID_3, LocalDateTime.of(2015, 5, 30, 19, 0), "Ужин", 1000);


    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
