package com.epam.training.movie.theater.security;

import com.epam.training.movie.theater.domain.Role;
import com.epam.training.movie.theater.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alyx on 09.04.2016.
 */
public class CustomUserDetails implements UserDetails {
    private final String userName;
    private final String userPassword;
    private final List<CustomGrantedAuthority> customGrantedAuthorityList;

    public CustomUserDetails(User user) {

        userName = user.getName();
        userPassword = user.getPassword();
        List<Role> roles = user.getRoles();

        List<CustomGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles){
            authorities.add(new CustomGrantedAuthority(role.name()));
        }

        customGrantedAuthorityList = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
