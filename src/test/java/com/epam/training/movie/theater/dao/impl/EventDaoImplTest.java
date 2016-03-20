/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.BaseTest;
import com.epam.training.movie.theater.aspect.CounterAspect;
import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.domain.Event;
import java.util.List;
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
public class EventDaoImplTest extends BaseTest {

    @Autowired
    EventDao eventDaoImpl;

    @Autowired
    CounterAspect counterAspect;
    
    public EventDaoImplTest() {
    }

    @Test
    public void shouldIncrementCounter() {
        String name = "saw";
        assertNull(counterAspect.getStorage().get("saw"));
        
        List<Event> byMovieName = eventDaoImpl.getByMovieName("saw");
        
        assertNotNull(counterAspect.getStorage().get("saw"));
        assertEquals(1L, counterAspect.getStorage().get("saw").get());
        
        byMovieName = eventDaoImpl.getByMovieName("saw");
        assertEquals(2L, counterAspect.getStorage().get("saw").get());
    }

}
