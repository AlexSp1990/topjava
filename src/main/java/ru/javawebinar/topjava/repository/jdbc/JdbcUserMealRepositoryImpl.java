package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    //private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int user_id) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories())
                .addValue("dateTime", userMeal.getDateTime())
                .addValue("user_id", user_id);

        if (userMeal.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            userMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description=:description, calories=:calories, dateTime=:dateTime " +
                            " WHERE id=:id and user_id=:user_id", map);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int user_id) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? and user_id = ?", id, user_id) != 0;
    }

    @Override
    public UserMeal get(int id, int user_id) {
        List<UserMeal> userMeals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? and user_id = ?",
                GetUserMealsResultSetExtractor(), id, user_id);
        return DataAccessUtils.singleResult(userMeals);
    }

    @Override
    public List<UserMeal> getAll(int user_id) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id = ? ORDER BY dateTime ", GetUserMealsResultSetExtractor() , user_id);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int user_id) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE dateTime >= ? and dateTime <= ? and user_id = ? ORDER BY dateTime DESC",
                GetUserMealsResultSetExtractor(), Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), user_id);
    }

    private static ResultSetExtractor<ArrayList<UserMeal>> GetUserMealsResultSetExtractor() {
        return (ResultSet rs)-> {
            Map<Integer, UserMeal> map = new HashMap<>();
            UserMeal userMeal = null;

            while (rs.next()) {
                Integer id = rs.getInt("id");
                userMeal = map.get(id);

                if (userMeal == null) {
                    userMeal = new UserMeal(id,
                            rs.getTimestamp("dateTime").toLocalDateTime(),
                            rs.getString("description"),
                            rs.getInt("calories"));
                    map.put(id, userMeal);
                }
            }

            return new ArrayList<>( map.values() );
        };
    }
}
