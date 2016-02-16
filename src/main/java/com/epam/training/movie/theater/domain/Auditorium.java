/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alyx
 */
public class Auditorium {

    private Long id;
    private String name;
    private int numberOfSeats;
    private Map<Integer, Boolean> vipSeats = new HashMap<>();
    private Map<Integer, Boolean> seats = new HashMap<>();

    public Auditorium(String name, int numberOfSeats, List<Integer> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        for (int i = 0; i < numberOfSeats; i++) {
            if (vipSeats.contains(i)) {
                this.vipSeats.put(i, Boolean.TRUE);
            } else {
                this.seats.put(i, Boolean.TRUE);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Map<Integer, Boolean> getVipSeats() {
        return vipSeats;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVipSeat(int seat) {
        return vipSeats.containsKey(seat);
    }
}
