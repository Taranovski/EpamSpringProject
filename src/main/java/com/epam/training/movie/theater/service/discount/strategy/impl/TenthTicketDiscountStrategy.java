/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount.strategy.impl;

import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import com.epam.training.movie.theater.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Every 10th ticket - give 50% for every 10th ticket purchased by user
 *
 * @author Alyx
 */
public class TenthTicketDiscountStrategy implements DiscountStrategy {

    private UserService userService;

    @Override
    public BigDecimal getDiscount(User user, Event event, DateTime date) {
        List<Ticket> tickets = userService.getBookedTickets(user);
        int ticketCount = tickets.size();

        if (ticketCount % 10 == 9) {
            return new BigDecimal("0.5");
        }
        return BigDecimal.ZERO;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
