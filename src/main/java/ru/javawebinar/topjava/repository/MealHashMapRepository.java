package ru.javawebinar.topjava.repository;

import static ru.javawebinar.topjava.data.DataHashMapMeals.*;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AlexS on 07.03.2016.
 */
public class MealHashMapRepository implements MealsRepository {
    @Override
    public UserMeal save(UserMeal userMeal) {
        //Integer locId = userMeal.getId() == null ? (userMealMap.keySet().stream().sorted((x, y)-> ((x > y) ? (-1) : (1))).collect(Collectors.toList()).get(0) + 1) : (userMeal.getId());
        return userMealMap.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int id) {
        return userMealMap.remove(id, userMealMap.get(id));
    }

    @Override
    public UserMeal get(int id) {
        return userMealMap.get(id);
    }

    @Override
    public List<UserMeal> getAll(int id) {
        return userMealMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteAll(int id) {
        userMealMap.clear();
    }
}
