/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.auditorium;

import com.epam.training.movie.theater.domain.Auditorium;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Returns info about auditoriums and places
 *
 * @author Alyx
 */
public class AuditoriumService {

    private List<Auditorium> auditoriums;
    private int seatsNumber;
    private Map<String, List<Integer>> vipSeats;

    public AuditoriumService(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
        vipSeats = new HashMap<>();
        for (Auditorium auditorium : auditoriums) {
            seatsNumber += auditorium.getNumberOfSeats();
            vipSeats.put(auditorium.getName(), auditorium.getVipSeats());
        }
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public Map<String, List<Integer>> getVipSeats() {
        return vipSeats;
    }

}
