/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount.strategy.impl;

import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import org.joda.time.DateTime;

/**
 * Birthday strategy - give 5% if user has birthday
 *
 * @author Alyx
 */
public class BirthDayDiscountStrategy implements DiscountStrategy {

    @Override
    public double getDiscount(User user, Event event, DateTime date) {
        DateTime birthDay = user.getBirthDay();
        if (birthDay.getMonthOfYear() == date.getMonthOfYear()
                && birthDay.getDayOfMonth() == date.getDayOfMonth()) {
            return 0.05;
        }
        return 0;
    }

}
