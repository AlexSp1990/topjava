package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by AlexS on 07.03.2016.
 */
public interface MealsRepository {
    // todoItem.user = null
    UserMeal save(UserMeal userMeal);

    // false if not found
    boolean delete(int id);

    // null if not found
    UserMeal get(int id);

    // ORDERED DESC BY PRIORITY
    List<UserMeal> getAll(int id);

    void deleteAll(int id);
}
