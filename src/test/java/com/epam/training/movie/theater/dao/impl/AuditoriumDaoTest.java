/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao.impl;

import com.epam.training.movie.theater.BaseTest;
import com.epam.training.movie.theater.domain.Auditorium;
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
public class AuditoriumDaoTest extends BaseTest {

    @Autowired
    AuditoriumDaoImpl auditoriumService;

    public AuditoriumDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldFillAuditoriumServiceWithAuditories() {
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();

        assertNotNull(auditoriums);
        assertEquals(3, auditoriums.size());

        assertEquals("Red blood", auditoriums.get(0).getName());
        assertEquals("Green grass", auditoriums.get(1).getName());
        assertEquals("Blue sky", auditoriums.get(2).getName());
    }

}
