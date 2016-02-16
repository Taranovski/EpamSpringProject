/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Manages tickets, prices, bookings
 *
 * @author Alyx
 */
public interface BookingService {

    /**
     * Returns ticket for specified event on specific date and time for
     * specified seats.
     *
     * @param event Event is needed to get base price, rating
     * @param date
     * @param user User is needed to calculate discount
     * @param seats Vip seats should cost more than regular seats
     * @return
     */
    Ticket getTicket(String event, DateTime date, User user, Integer... seats);

    /**
     * User could be registered or not. If user is registered, then booking
     * information is stored for that user. Purchased tickets for particular
     * event should be stored
     *
     * @param user
     * @param ticket
     */
    void bookTicket(User user, Ticket ticket);

    /**
     * get all purchased tickets for event for specific date
     *
     * @param event
     * @param date
     * @return
     */
    List<Ticket> getTicketsForEvent(String event, DateTime date);
}
