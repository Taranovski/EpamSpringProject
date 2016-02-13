/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import java.util.List;

/**
 * Manages registered users
 *
 * @author Alyx
 */
public interface UserService {

    List<Ticket> getBookedTickets(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    User getUserByName(String userName);

    User register(User user);

    void remove(User user);

}
