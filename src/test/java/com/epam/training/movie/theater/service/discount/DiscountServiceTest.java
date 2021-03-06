/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.discount;

import com.epam.training.movie.theater.BaseTest;
import com.epam.training.movie.theater.service.DiscountService;
import com.epam.training.movie.theater.service.discount.strategy.DiscountStrategy;

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
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        locations = {"/test-MovieTheatre-servlet.xml"},
//        loader = GenericXmlContextLoader.class
//)
public class DiscountServiceTest extends BaseTest {

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
    }

}
