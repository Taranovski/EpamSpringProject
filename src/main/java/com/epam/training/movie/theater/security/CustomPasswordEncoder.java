package com.epam.training.movie.theater.security;

/**
 * Created by Alyx on 10.04.2016.
 */
public class CustomPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return String.valueOf(rawPassword).equals(String.valueOf(encodedPassword));
    }
}
