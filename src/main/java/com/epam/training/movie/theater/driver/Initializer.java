package com.epam.training.movie.theater.driver;

import com.epam.training.movie.theater.dao.AuditoriumDao;
import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.dao.MovieDao;
import com.epam.training.movie.theater.domain.Movie;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
        Map map = objectMapper.readValue(Initializer.class.getClassLoader().getResourceAsStream("initialization.json"), Map.class);

        List<Map> movies = (List) map.get("movies");

        for (Map map1 : movies) {
            Movie movie = new Movie();
            movie.setName((String) map1.get("name"));
            movie.setRating(new BigDecimal((Integer) map1.get("rating")));

            movieDao.create(movie);
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
