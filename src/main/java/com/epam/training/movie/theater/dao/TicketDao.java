/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.Ticket;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Alyx
 */
public interface TicketDao {

    List<Ticket> getTicketsByUserId(Long userId);

    List<Ticket> getTicketsForMovieAndDate(String name, DateTime date);

    Ticket save(Ticket ticket);

}
