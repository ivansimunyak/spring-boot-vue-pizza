package com.ivan.pizzaplace.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String jwttoken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.authorities = authorities;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public Collection<?> getAuthorities() {
        return authorities;
    }
}