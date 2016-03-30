package com.epam.training.movie.theater.driver;

import com.epam.training.movie.theater.dao.AuditoriumDao;
import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.dao.MovieDao;
import com.epam.training.movie.theater.domain.Auditorium;
import com.epam.training.movie.theater.domain.Event;
import com.epam.training.movie.theater.domain.Movie;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Alyx on 27.03.2016.
 */
public class Initializer {

    private EventDao eventDao;
    private MovieDao movieDao;
    private AuditoriumDao auditoriumDao;

    public void initialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = Initializer.class.getClassLoader().getResourceAsStream("initialization.json");
        Map initialData = objectMapper.readValue(resourceAsStream, Map.class);

        List<Map> movies = (List) initialData.get("movies");

        for (Map map1 : movies) {
            Movie movie = new Movie();
            movie.setName((String) map1.get("name"));
            movie.setRating(new BigDecimal((String) map1.get("rating")));

            movieDao.create(movie);
        }

        List<Map> events = (List<Map>) initialData.get("events");

        for (Map eventProperties : events) {
            String movieName = (String) eventProperties.get("movie");
            Movie movieByName = movieDao.getMovieByName(movieName);

            String auditoriumName = (String) eventProperties.get("auditorium");
            Auditorium auditorium = auditoriumDao.getByName(auditoriumName);

            Event event = new Event();
            event.setAuditorium(auditorium);
            event.setMovie(movieByName);
            BigDecimal basePrice = new BigDecimal((String) eventProperties.get("basePrice"));
            event.setBasePrice(basePrice);
            DateTime startTime = new DateTime(Long.valueOf((String) eventProperties.get("startTime")));
            event.setStartTime(startTime);
            DateTime endTime = new DateTime(Long.valueOf((String) eventProperties.get("endTime")));
            event.setEndTime(endTime);

            eventDao.create(event);
        }
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
