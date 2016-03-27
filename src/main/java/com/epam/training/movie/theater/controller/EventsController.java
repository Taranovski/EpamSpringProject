package com.epam.training.movie.theater.controller;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Alyx on 24.03.2016.
 */
@Controller
@RequestMapping("events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllEvents() {
        List<Event> allEvents = eventService.getAllEvents();
        return new ModelAndView("events", "events", allEvents);
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
