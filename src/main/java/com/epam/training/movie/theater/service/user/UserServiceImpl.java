 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.service.user;

import com.epam.training.movie.theater.dao.impl.TicketDaoImpl;
import com.epam.training.movie.theater.dao.impl.UserDaoImpl;
import com.epam.training.movie.theater.domain.Ticket;
import com.epam.training.movie.theater.domain.User;
import com.epam.training.movie.theater.service.UserService;
import java.util.List;

/**
 * Manages registered users
 *
 * @author Alyx
 */
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;
    private TicketDaoImpl ticketDao;

    @Override
    public User register(User user) {
        return userDao.save(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getByName(userName);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        Long userId = user.getId();
        return ticketDao.getTicketsByUserId(userId);
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public TicketDaoImpl getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDaoImpl ticketDao) {
        this.ticketDao = ticketDao;
    }

}
