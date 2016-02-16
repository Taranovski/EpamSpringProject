/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.dao;

import com.epam.training.movie.theater.domain.Auditorium;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface AuditoriumDao {

    List<Auditorium> getAllAuditoriums();

    Auditorium getById(Long id);

    Auditorium getByName(String name);
}
