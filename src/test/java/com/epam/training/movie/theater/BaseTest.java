package com.epam.training.movie.theater;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * Created by Alyx on 20.03.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"/test-MovieTheatre-servlet.xml"},
        loader = GenericXmlContextLoader.class
)
public class BaseTest {

    @Test
    public void runnableTest() {

    }
}
