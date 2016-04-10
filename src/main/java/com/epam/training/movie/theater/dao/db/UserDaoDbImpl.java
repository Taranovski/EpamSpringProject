/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.db;

import com.epam.training.movie.theater.dao.UserDao;
import com.epam.training.movie.theater.domain.Role;
import com.epam.training.movie.theater.domain.User;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UserDaoDbImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private AtomicLong counter = new AtomicLong(1L);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void init() {
        destroy();
        try {
            jdbcTemplate.execute("create table \"users\" ("
                    + "id INTEGER, "
                    + "name VARCHAR(50), "
                    + "email VARCHAR(100), "
                    + "password VARCHAR(100), "
                    + "roles VARCHAR(100), "
                    + "birthDay DATE)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void destroy() {
        try {
            jdbcTemplate.execute("drop table \"users\"");
        } catch (Exception e) {
        }
    }

    public UserDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    final static RowMapper<User> rowMapper = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet resultSet, int arg1) throws SQLException {
            User user = new User();

            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            Date date = resultSet.getDate("birthDay");
            user.setBirthDay(new DateTime(date.getTime()));
            user.setRegistered(true);
            user.setPassword(resultSet.getString("password"));
            String roles = resultSet.getString("roles");
            List<Role> roleList = new ArrayList<>();
            for (String role : roles.split(" ")){
                roleList.add(Role.valueOf(role));
            }

            user.setRoles(roleList);
            return user;
        }
    };

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select * from \"users\" where email = ?",
                new Object[]{email}, rowMapper);
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from \"users\" where id = ?",
                new Object[]{id}, rowMapper);
    }

    @Override
    public User getByName(String userName) {
        User user = jdbcTemplate.queryForObject(
                "select * from \"users\" where name = ?",
                new Object[]{userName}, rowMapper);
        return user;
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public User save(User user) {
        user.setId(counter.incrementAndGet());
        jdbcTemplate.update("insert into \"users\" (id, name, email, birthDay, password, roles) values (?, ?, ?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getEmail(),
                new Date(user.getBirthDay().getMillis()),
                user.getPassword(),
                user.getRoles().toString().replace("[", "").replace("]", "").replace(",", "")
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
