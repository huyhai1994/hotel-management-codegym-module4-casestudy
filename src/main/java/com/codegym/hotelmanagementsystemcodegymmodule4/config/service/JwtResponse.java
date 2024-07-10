package com.codegym.hotelmanagementsystemcodegymmodule4.config.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(Long id, String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }
}