/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Alyx
 */
public class MovieDao {
    Map<Long, Movie> movies;
    AtomicLong counter;

    public MovieDao() {
        movies = new ConcurrentHashMap<>();
        counter = new AtomicLong();
    }

    public Movie getMovieByName(String name) {
        for (Movie movie : movies.values()) {
            if (name.equals(movie.getName())) {
                return movie;
            }
        }
        return null;
    }

    public Movie create(Movie movie) {
        long incrementAndGet = counter.incrementAndGet();
        movie.setId(incrementAndGet);
        movies.put(incrementAndGet, movie);
        return movie;
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }
}
