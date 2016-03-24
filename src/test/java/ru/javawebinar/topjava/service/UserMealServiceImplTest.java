package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
/**
 * Created by AlexS on 20.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceImplTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(USER_MEAL_ID_1, USER_ID);
        MATCHER.assertEquals(USER_MEAL_1, userMeal);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_MEAL_ID_1, USER_ID);
        service.delete(USER_MEAL_ID_2, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL_3), service.getAll(USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID).stream()
                .sorted((f1, f2) -> f2.getDateTime()
                        .compareTo(f1.getDateTime())).collect(Collectors.toList());
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1), all);
    }

    @Test
    public void testUpdate() throws Exception {
//        UserMeal updated = new UserMeal(USER_MEAL_1);
//        updated.setName("UpdatedName");
//        updated.setCaloriesPerDay(330);
//        service.update(updated.asUser());
//        MATCHER.assertEquals(updated, service.get(USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal newUserMeal = new UserMeal(null, LocalDateTime.of(2015, 6, 30, 10, 0), "Завтрак", 500);
        UserMeal created = service.save(newUserMeal, USER_ID);
        newUserMeal.setId(created.getId());
        Collection<UserMeal> all = service.getAll(USER_ID).stream()
                .sorted((f1, f2) -> f2.getDateTime()
                        .compareTo(f1.getDateTime())).collect(Collectors.toList());
        MATCHER.assertCollectionEquals(Arrays.asList(newUserMeal, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1),
                all);
    }
}










