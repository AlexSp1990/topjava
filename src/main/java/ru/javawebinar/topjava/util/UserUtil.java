package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AlexS on 11.03.2016.
 */
public class UserUtil {
    public static final List<User> USER_LIST = Arrays.asList(
            new User(1, "Alex", "alexandr@mail.ru", "alex", Role.ROLE_USER),
            new User(2, "Admin", "admin@mail.ru", "admin", Role.ROLE_ADMIN),
            new User(3, "User", "user@mail.ru", "user", Role.ROLE_USER)
    );
}
