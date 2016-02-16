/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.Event;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Manages events (movie shows). Event contains only basic information, like
 * name, base price for tickets, rating (high, mid, low). Event can be presented
 * on several dates and several times within each day. For each dateTime an
 * Event will be presented only in single auditorium.
 *
 * @author Alyx
 */
public interface EventDao {

    Event create(Event event);

    void remove(Event event);

    Event getById(Long id);

    List<Event> getByMovieName(String name);

    List<Event> getAll();

    Event getByMovieNameAndDate(String event, DateTime date);
}
