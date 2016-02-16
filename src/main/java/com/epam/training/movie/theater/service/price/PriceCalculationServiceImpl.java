/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.price;

import com.epam.training.movie.theater.domain.Auditorium;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import com.epam.training.movie.theater.service.DiscountService;
import com.epam.training.movie.theater.service.PriceCalculationService;
import java.math.BigDecimal;

public class PriceCalculationServiceImpl implements PriceCalculationService {

    BigDecimal highRatingTreshold;
    BigDecimal highRatingRate;
    BigDecimal vipSeatRate;
    private DiscountService discountService;

    @Override
    public BigDecimal getPrice(Event event, User user, Integer[] seats) {
        Auditorium auditorium = event.getAuditorium();
        BigDecimal rating = event.getMovie().getRating();
        BigDecimal basePrice = event.getBasePrice();

        BigDecimal summ = BigDecimal.ZERO;
        for (Integer seat : seats) {
            BigDecimal ticketPrice = basePrice;
            if (auditorium.isVipSeat(seat)) {
                ticketPrice = ticketPrice.multiply(vipSeatRate);
            }
            if (rating.compareTo(highRatingRate) >= 0) {
                ticketPrice = ticketPrice.multiply(highRatingRate);
            }
            summ = summ.add(ticketPrice);
        }

        BigDecimal discount = discountService.getDiscount(user, event);
        summ = summ.subtract(summ.multiply(discount));

        return summ;
    }

}
