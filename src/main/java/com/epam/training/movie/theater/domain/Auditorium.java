/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.domain;

import java.util.List;

/**
 *
 * @author Alyx
 */
public class Auditorium {

    private String name;
    private int numberOfSeats;
    private List<Integer> vipSeats;

    public Auditorium(String name, int numberOfSeats, List<Integer> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
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

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(List<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }

}
