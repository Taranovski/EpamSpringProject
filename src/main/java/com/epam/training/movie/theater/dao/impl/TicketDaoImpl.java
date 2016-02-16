/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.dao.TicketDao;
import com.epam.training.movie.theater.domain.Ticket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.joda.time.DateTime;

/**
 *
 * @author Alyx
 */
public class TicketDaoImpl implements TicketDao {

    Map<Long, Ticket> tickets = new HashMap<>();
    AtomicLong counter = new AtomicLong();

    @Override
    public List<Ticket> getTicketsByUserId(Long userId) {
        List<Ticket> list = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (userId.equals(ticket.getUser().getId())) {
                list.add(ticket);
            }
        }
        return list;
    }

    @Override
    public List<Ticket> getTicketsForMovieAndDate(String name, DateTime date) {
        List<Ticket> list = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (name.equals(ticket.getEvent().getMovie().getName())
                    && date.equals(ticket.getEvent().getStartTime())) {
                list.add(ticket);
            }
        }
        return list;
    }

    @Override
    public Ticket save(Ticket ticket) {

        if (ticket.getId() != null) {
            throw new RuntimeException();
        }
        long incrementAndGet = counter.incrementAndGet();
        ticket.setId(incrementAndGet);
        tickets.put(incrementAndGet, ticket);
        return ticket;
    }

}
