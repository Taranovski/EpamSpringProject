/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.domain.Event;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface EventService {

    Event create(Event event);

    void remove(Event event);

    List<Event> getByName(String name);

    List<Event> getAll();
    
}
