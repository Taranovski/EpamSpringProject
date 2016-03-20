/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.user;

import com.epam.training.movie.theater.BaseTest;
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
public class UserServiceImplTest extends BaseTest {

    @Autowired
    UserServiceImpl userService;

    public UserServiceImplTest() {
    }

    @Test
    public void shouldResolveDependencies() {
        assertNotNull(userService.getTicketDao());
        assertNotNull(userService.getUserDao());
    }

}
