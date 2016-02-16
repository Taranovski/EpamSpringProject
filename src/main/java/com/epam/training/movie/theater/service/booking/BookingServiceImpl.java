/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.booking;

import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.dao.TicketDao;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import com.epam.training.movie.theater.service.BookingService;
import com.epam.training.movie.theater.service.PriceCalculationService;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;

public class BookingServiceImpl implements BookingService {

    private PriceCalculationService priceCalculationService;
    private TicketDao ticketDao;
    private EventDao eventDao;

    @Override
    public Ticket getTicket(String event, DateTime date, User user, Integer... seats) {
        Ticket ticket = new Ticket();
        Event byMovieNameAndDate = eventDao.getByMovieNameAndDate(event, date);

        ticket.setEvent(byMovieNameAndDate);
        ticket.setUser(user);

        BigDecimal price = priceCalculationService.getPrice(byMovieNameAndDate, user, seats);

        ticket.setPrice(price);

        return ticket;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {
        if (user.isRegistered()) {
            ticketDao.save(ticket);
        }
    }

    @Override
    public List<Ticket> getTicketsForEvent(String event, DateTime date) {
        return ticketDao.getTicketsForMovieAndDate(event, date);
    }

    public void setPriceCalculationService(PriceCalculationService priceCalculationService) {
        this.priceCalculationService = priceCalculationService;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

}
