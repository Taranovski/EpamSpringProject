package com.epam.training.movie.theater.driver;

import com.epam.training.movie.theater.dao.AuditoriumDao;
import com.epam.training.movie.theater.dao.EventDao;
import com.epam.training.movie.theater.dao.MovieDao;
import com.epam.training.movie.theater.dao.UserDao;
import com.epam.training.movie.theater.domain.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alyx on 27.03.2016.
 */
public class Initializer {

    private EventDao eventDao;
    private MovieDao movieDao;
    private AuditoriumDao auditoriumDao;
    private UserDao userDao;

    public void initialize() throws IOException {
        Map initialData = getInitializationData();

        initializeMovies(initialData);

        initializeEvents(initialData);

        initializeUsers(initialData);
    }

    /**
     * initializes users
     *
     * @param initialData
     */
    private void initializeUsers(Map initialData) {
        List<Map> users = (List<Map>) initialData.get("users");

        for (Map user : users) {
            User newUserToSave = new User();

            newUserToSave.setName((String) user.get("name"));
            newUserToSave.setEmail((String) user.get("email"));
            DateTime birthday = new DateTime(Long.valueOf((String) user.get("birthday")));
            newUserToSave.setBirthDay(birthday);
            newUserToSave.setRegistered(true);

            String userName = newUserToSave.getName();
            String userOpenPassword = (String) user.get("password");

            newUserToSave.setPassword(getCipheredPassword(userName, userOpenPassword));

            List<String> roles = (List<String>) user.get("roles");
            List<Role> roleList = new ArrayList<>();
            if (roles != null && !roles.isEmpty()) {
                for (String role : roles) {
                    roleList.add(Role.valueOf(role));
                }
            }
            newUserToSave.setRoles(roleList);

            userDao.save(newUserToSave);
        }

    }

    private String getCipheredPassword(String userName, String userOpenPassword) {
//        return userName + (userName + userOpenPassword + userName).hashCode();
        return userOpenPassword;
    }

    /**
     * initializes events
     * depends on {@link Initializer#initializeMovies(java.util.Map)} execution
     *
     * @param initialData
     */
    private void initializeEvents(Map initialData) {
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

    /**
     * initializes movies
     *
     * @param initialData
     */
    private void initializeMovies(Map initialData) {
        List<Map> movies = (List<Map>) initialData.get("movies");

        for (Map map1 : movies) {
            Movie movie = new Movie();
            movie.setName((String) map1.get("name"));
            movie.setRating(new BigDecimal((String) map1.get("rating")));

            movieDao.create(movie);
        }
    }

    /**
     * @return
     * @throws IOException
     */
    private Map getInitializationData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceAsStream = Initializer.class.getClassLoader().getResourceAsStream("initialization.json");
        return objectMapper.readValue(resourceAsStream, Map.class);
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

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
