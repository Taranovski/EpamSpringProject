/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Alyx
 */
public class UserDao {

    Map<Long, User> storage = new HashMap<>();
    AtomicLong counter = new AtomicLong();

    public User save(User user) {
        if (user.getId() != null) {
            throw new RuntimeException();
        }
        long incrementAndGet = counter.incrementAndGet();
        user.setId(incrementAndGet);
        storage.put(incrementAndGet, user);
        return user;
    }

    public void remove(User user) {
        if (user.getId() == null) {
            throw new RuntimeException();
        }
        storage.remove(user.getId());
    }

    public User getById(Long id) {
        return storage.get(id);
    }

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
