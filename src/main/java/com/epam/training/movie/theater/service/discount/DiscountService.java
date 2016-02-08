/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount;

import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.User;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Counts different discounts for purchased tickets
 *
 * @author Alyx
 */
public class DiscountService {

    private List<DiscountStrategy> discountStrategies;

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
     * @param date
     * @return
     */
    public double getDiscount(User user, Event event, DateTime date) {
        double maxDiscount = 0;
        for (DiscountStrategy discountStrategy : discountStrategies) {
            double tempDiscount = discountStrategy.getDiscount(user, event, date);
            if (tempDiscount > maxDiscount) {
                maxDiscount = tempDiscount;
            }
        }
        return maxDiscount;
    }

}
