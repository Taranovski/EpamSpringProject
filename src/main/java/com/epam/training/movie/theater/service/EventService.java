package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.domain.Event;

import java.util.List;

/**
 * Created by Alyx on 24.03.2016.
 */
public class EventService {

    //    @Inject
    private EventDao eventDao;

    public List<Event> getAllEvents() {
        return eventDao.getAll();
    }


    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
