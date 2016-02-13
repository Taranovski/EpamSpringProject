/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import org.joda.time.DateTime;

/**
 *
 * @author Alyx
 */
public interface DiscountService {

    /**
     * returns discount for each ticket for the user on particular event
     *
     * @param user
     * @param event
     * @param date
     * @return
     */
    double getDiscount(User user, Event event, DateTime date);
    
}
