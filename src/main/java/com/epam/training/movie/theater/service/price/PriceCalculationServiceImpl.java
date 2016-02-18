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
import java.math.MathContext;
import java.math.RoundingMode;

public class PriceCalculationServiceImpl implements PriceCalculationService {

    private BigDecimal highRatingTreshold;
    private BigDecimal highRatingRate;
    private BigDecimal vipSeatRate;
    private DiscountService discountService;
    private static final MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);

    @Override
    public BigDecimal getPrice(Event event, User user, Integer[] seats) {
        Auditorium auditorium = event.getAuditorium();
        BigDecimal rating = event.getMovie().getRating();
        BigDecimal basePrice = event.getBasePrice();

        BigDecimal summ = BigDecimal.ZERO;
        for (Integer seat : seats) {
            BigDecimal ticketPrice = basePrice;
            if (auditorium.isVipSeat(seat)) {
                ticketPrice = ticketPrice.multiply(vipSeatRate, mathContext);
            }
            if (rating.compareTo(highRatingTreshold) >= 0) {
                ticketPrice = ticketPrice.multiply(highRatingRate, mathContext);
            }
            summ = summ.add(ticketPrice, mathContext);
        }

        BigDecimal discount = discountService.getDiscount(user, event);
        summ = summ.subtract(summ.multiply(discount, mathContext), mathContext);

        return summ;
    }

    public void setHighRatingTreshold(BigDecimal highRatingTreshold) {
        this.highRatingTreshold = highRatingTreshold;
    }

    public void setHighRatingRate(BigDecimal highRatingRate) {
        this.highRatingRate = highRatingRate;
    }

    public void setVipSeatRate(BigDecimal vipSeatRate) {
        this.vipSeatRate = vipSeatRate;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

}
