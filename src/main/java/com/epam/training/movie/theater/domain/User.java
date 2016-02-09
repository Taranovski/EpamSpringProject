/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.domain;

import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Alyx
 */
public class User {
    private DateTime birthDay;
    private List<Ticket> tickets;

    public DateTime getBirthDay() {
        return birthDay;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setBirthDay(DateTime birthDay) {
        this.birthDay = birthDay;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
}
