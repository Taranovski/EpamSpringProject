/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.booking;

import com.epam.training.movie.theater.dao.AuditoriumDao;
import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.dao.TicketDao;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.Movie;
import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import org.joda.time.DateTime;
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
        locations = {"/spring.xml"},
        loader = GenericXmlContextLoader.class
)
public class BookingServiceImplTest {

    @Autowired
    BookingServiceImpl bookingServiceImpl;

    @Autowired
    EventDao eventDao;

    @Autowired
    AuditoriumDao auditoriumDao;

    @Autowired
    TicketDao ticketDao;

    public BookingServiceImplTest() {
    }

    @Test
    public void shouldResolveDeppendencies() {
        assertNotNull(bookingServiceImpl.getEventDao());
        assertNotNull(bookingServiceImpl.getPriceCalculationService());
        assertNotNull(bookingServiceImpl.getTicketDao());
    }

    @Test
    public void shouldGiveATicket() {
        Event event = new Event();
        event.setAuditorium(auditoriumDao.getByName("Red blood"));
        event.setBasePrice(BigDecimal.TEN);
        event.setStartTime(new DateTime(1, 1, 1, 2, 0));
        Movie movie = new Movie();
        movie.setName("saw");
        movie.setRating(BigDecimal.TEN);
        event.setMovie(movie);

        eventDao.create(event);
        User user = new User();
        user.setId(1L);
        user.setRegistered(true);
        user.setBirthDay(new DateTime(1, 1, 1, 2, 0));
        Ticket ticket = bookingServiceImpl.getTicket("saw", new DateTime(1, 1, 1, 2, 0), user, 1, 2, 3);

        assertEquals(new BigDecimal("85.5", new MathContext(2, RoundingMode.HALF_UP)), ticket.getPrice());

        List<Ticket> ticketsByUserId = ticketDao.getTicketsByUserId(1L);
        assertTrue(ticketsByUserId.isEmpty());

        bookingServiceImpl.bookTicket(user, ticket);

        ticketsByUserId = ticketDao.getTicketsByUserId(1L);
        assertFalse(ticketsByUserId.isEmpty());

        assertEquals(1, ticketsByUserId.size());
    }
}
