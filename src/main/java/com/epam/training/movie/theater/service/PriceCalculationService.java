/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service;

import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import java.math.BigDecimal;

/**
 *
 * @author Alyx
 */
public interface PriceCalculationService {

    BigDecimal getPrice(Event event, User user, Integer[] seats);

}
