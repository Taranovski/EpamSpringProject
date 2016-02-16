/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.dao.UserDao;
import com.epam.training.movie.theater.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Alyx
 */
public class UserDaoImpl implements UserDao {

    Map<Long, User> storage = new HashMap<>();
    AtomicLong counter = new AtomicLong();

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            throw new RuntimeException();
        }
        long incrementAndGet = counter.incrementAndGet();
        user.setId(incrementAndGet);
        storage.put(incrementAndGet, user);
        return user;
    }

    @Override
    public void remove(User user) {
        if (user.getId() == null) {
            throw new RuntimeException();
        }
        storage.remove(user.getId());
    }

    @Override
    public User getById(Long id) {
        return storage.get(id);
    }

    @Override
    public User getByEmail(String email) {
        if (email == null){
            return null;
        }
        for (User user : storage.values()){
            if (email.equals(user.getEmail())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByName(String userName) {
        if (userName == null){
            return null;
        }
        for (User user : storage.values()){
            if (userName.equals(user.getName())){
                return user;
            }
        }
        return null;
    }

}
