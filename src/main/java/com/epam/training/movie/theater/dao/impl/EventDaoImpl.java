/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.dao.EventDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.joda.time.DateTime;

public class EventDaoImpl implements EventDao {

    Map<Long, Event> events = new HashMap<>();
    AtomicLong counter = new AtomicLong();

    @Override
    public Event create(Event event) {
        long incrementAndGet = counter.incrementAndGet();
        event.setId(incrementAndGet);
        events.put(incrementAndGet, event);
        return event;
    }

    @Override
    public void remove(Event event) {
        events.remove(event.getId(), event);
    }

    @Override
    public List<Event> getByMovieName(String name) {
        Collection<Event> storedEvents = events.values();
        List<Event> eventsByMovieName = new ArrayList<>();
        for (Event event : storedEvents) {
            if (name.equals(event.getMovie().getName())) {
                eventsByMovieName.add(event);
            }
        }
        return eventsByMovieName;
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Event getById(Long id) {
        return events.get(id);
    }

    @Override
    public Event getByMovieNameAndDate(String name, DateTime dateTime) {
        Collection<Event> storedEvents = events.values();
        for (Event event : storedEvents) {
            if (name.equals(event.getMovie().getName()) && dateTime.equals(event.getStartTime())) {
                return event;
            }
        }
        return null;
    }

}
