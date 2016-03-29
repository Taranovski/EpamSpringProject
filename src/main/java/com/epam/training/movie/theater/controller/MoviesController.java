package com.epam.training.movie.theater.controller;

import com.epam.training.movie.theater.dao.MovieDao;
import com.epam.training.movie.theater.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Alyx on 27.03.2016.
 */
@Controller
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MovieDao movieDao;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Accept=application/json")
    @ResponseBody
    public List<Movie> getAllMovies(){
        return movieDao.getAllMovies();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.TEXT_HTML, headers = "Accept=text/html")
    @ResponseBody
    public List<Movie> getAllMovies1(){
        return movieDao.getAllMovies();
    }
}
