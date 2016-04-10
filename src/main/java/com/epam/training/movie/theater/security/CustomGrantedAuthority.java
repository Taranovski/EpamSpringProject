package com.epam.training.movie.theater.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Alyx on 09.04.2016.
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    private final String authority;

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
