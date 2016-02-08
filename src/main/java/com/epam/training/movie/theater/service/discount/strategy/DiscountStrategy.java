/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount.strategy;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import org.joda.time.DateTime;

/**
 * single class with logic for calculating discount
 *
 * @author Alyx
 */
public interface DiscountStrategy {

    double getDiscount(User user, Event event, DateTime date);

}
