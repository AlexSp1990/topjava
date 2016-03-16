package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(userMeal -> this.save(userMeal, LoggedUser.id()));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        userId = LoggedUser.id();
        final int finalUserId = userId;
        userMeal.setUser(UserUtil.USER_LIST.stream().filter(user -> user.getId() == finalUserId).findFirst().get());
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return LoggedUser.id() == repository.get(id).getUser().getId() && repository.remove(id, repository.get(id));
    }

    @Override
    public UserMeal get(int id, int userId) {
        return LoggedUser.id() == repository.get(id).getUser().getId() ? repository.get(id) : null;
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        return repository.values().stream().
                filter(userMeal -> userId == userMeal.getUser().getId()).sorted((userMeal1, userMeal2) -> userMeal1.getDateTime().compareTo(userMeal2.getDateTime())).collect(Collectors.toList());
    }
}

