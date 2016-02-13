/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.auditorium;

import com.epam.training.movie.theater.service.AuditoriumService;
import com.epam.training.movie.theater.domain.Auditorium;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Returns info about auditoriums and places
 *
 * @author Alyx
 */
public class AuditoriumServiceImpl implements AuditoriumService {

    private List<Auditorium> auditoriums;
    private int seatsNumber;
    private Map<String, List<Integer>> vipSeats;

    public AuditoriumServiceImpl(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
        vipSeats = new HashMap<>();
        for (Auditorium auditorium : auditoriums) {
            seatsNumber += auditorium.getNumberOfSeats();
            vipSeats.put(auditorium.getName(), auditorium.getVipSeats());
        }
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public int getSeatsNumber() {
        return seatsNumber;
    }

    @Override
    public Map<String, List<Integer>> getVipSeats() {
        return vipSeats;
    }

}
