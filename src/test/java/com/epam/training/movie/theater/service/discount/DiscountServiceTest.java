/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount;

import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;
import com.epam.training.movie.theater.service.discount.strategy.impl.BirthDayDiscountStrategy;
import com.epam.training.movie.theater.service.discount.strategy.impl.TenthTicketDiscountStrategy;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 *
 * @author Alyx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"/discount-test.xml"},
        loader = GenericXmlContextLoader.class
)
public class DiscountServiceTest {

    @Autowired
    DiscountService discountService;

    public DiscountServiceTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldFillDiscountServiceWithStrategies() {
        List<DiscountStrategy> discountStrategies = discountService.getDiscountStrategies();
        assertNotNull(discountStrategies);
        assertEquals(2, discountStrategies.size());
        assertTrue(discountStrategies.get(0) instanceof BirthDayDiscountStrategy);
        assertTrue(discountStrategies.get(1) instanceof TenthTicketDiscountStrategy);
    }

}
