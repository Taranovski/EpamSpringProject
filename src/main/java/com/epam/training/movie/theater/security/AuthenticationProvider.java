package com.epam.training.movie.theater.security;

import com.epam.training.movie.theater.dao.UserDao;
import com.epam.training.movie.theater.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Alyx on 09.04.2016.
 */
public class AuthenticationProvider implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byName = userDao.getByName(username);

        return new CustomUserDetails(byName);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
