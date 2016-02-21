/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount;

import com.epam.training.movie.theater.service.DiscountService;
import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import java.math.BigDecimal;
import java.util.List;

/**
 * Counts different discounts for purchased tickets
 *
 * @author Alyx
 */
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    @Override
    public List<DiscountStrategy> getDiscountStrategies() {
        return discountStrategies;
    }

    public void setDiscountStrategies(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    /**
     * returns discount for each ticket for the user on particular event
     *
     * @param user
     * @param event
     * @return
     */
    @Override
    public BigDecimal getDiscount(User user, Event event) {
        BigDecimal maxDiscount = BigDecimal.ZERO;
        for (DiscountStrategy discountStrategy : discountStrategies) {
            BigDecimal tempDiscount = discountStrategy.getDiscount(user, event, event.getStartTime());
            if (tempDiscount.compareTo(maxDiscount) > 0) {
                maxDiscount = tempDiscount;
            }
        }
        return maxDiscount;
    }

}
