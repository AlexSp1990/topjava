package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        System.out.println(getFilteredMealsWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(19,0), 2000));
        System.out.println(mealList);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> dateAndSumCalor = mealList.stream().collect(Collectors.groupingBy(userMeal1 -> userMeal1.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream().map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(),
                userMeal.getDescription(), userMeal.getCalories(), dateAndSumCalor.get(userMeal.getDateTime().toLocalDate()) <= caloriesPerDay)).
                filter(userMealWithExceed ->
                        LocalTime.of(userMealWithExceed.getDateTime().getHour(), userMealWithExceed.getDateTime().getMinute()).isAfter( startTime) &&
                                LocalTime.of(userMealWithExceed.getDateTime().getHour(), userMealWithExceed.getDateTime().getMinute()).isBefore( endTime)).
                collect(Collectors.toList());

    }
}
