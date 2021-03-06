/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.User;

/**
 *
 * @author Alyx
 */
public interface UserDao {

    User getByEmail(String email);

    User getById(Long id);

    User getByName(String userName);

    void remove(User user);

    User save(User user);
    
}
