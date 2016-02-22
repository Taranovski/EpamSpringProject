/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.db;

import com.epam.training.movie.theater.dao.UserDao;
import com.epam.training.movie.theater.domain.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoDbImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private AtomicLong counter = new AtomicLong(1L);

    private void init() {
        jdbcTemplate.execute("create table \"users\" ("
                + "id INTEGER, "
                + "name VARCHAR(50), "
                + "email VARCHAR(100), "
                + "birthDay DATE)");
    }

    public UserDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    final RowMapper<User> rowMapper = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet resultSet, int arg1) throws SQLException {
            User user = new User();

            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            Date date = resultSet.getDate("birthDay");
            user.setBirthDay(new DateTime(date.getTime()));
            user.setRegistered(true);
            return user;
        }
    };

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select * from \"users\" where 'email' = ?",
                new Object[]{email}, rowMapper);
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from \"users\" where 'id' = ?",
                new Object[]{id}, rowMapper);
    }

    @Override
    public User getByName(String userName) {
        return jdbcTemplate.queryForObject(
                "select * from \"users\" where 'name' = ?",
                new Object[]{userName}, rowMapper);
    }

    @Override
    public void remove(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("no such user found");
        }
        jdbcTemplate.update("delete from \"users\" where id = ?",
                user.getId()
        );
    }

    @Override
    public User save(User user) {
        user.setId(counter.incrementAndGet());
        jdbcTemplate.update("insert into \"users\" (id, name, email, birthDay) values (?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getEmail(),
                new Date(user.getBirthDay().getMillis())
        );
        return user;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
