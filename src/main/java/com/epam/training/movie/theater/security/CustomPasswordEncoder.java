package com.epam.training.movie.theater.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Alyx on 10.04.2016.
 */
public class CustomPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

    public static final String ALGORITHM_NAME = "SHA-256";

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ALGORITHM_NAME);
            byte[] digest = instance.digest(rawPassword.toString().getBytes());
            return new String(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no security algorithm found: " + ALGORITHM_NAME);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
