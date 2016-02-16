/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.dao.AuditoriumDao;
import com.epam.training.movie.theater.domain.Auditorium;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Returns info about auditoriums and places
 *
 * @author Alyx
 */
public class AuditoriumDaoImpl implements AuditoriumDao {

    private Map<Long, Auditorium> auditoriums;
    AtomicLong counter = new AtomicLong();

    public AuditoriumDaoImpl(Map<Long, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
        Long max = Collections.max(auditoriums.keySet());
        counter.set(max);
    }

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return new ArrayList<>(auditoriums.values());
    }

    @Override
    public Auditorium getById(Long id) {
        return auditoriums.get(id);
    }

    @Override
    public Auditorium getByName(String name) {
        for (Auditorium auditorium : auditoriums.values()) {
            if (name.equals(auditorium.getName())) {
                return auditorium;
            }
        }
        return null;
    }

    public Map<Long, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(Map<Long, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
