package com.codegym.hotelmanagementsystemcodegymmodule4.config.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String email;
    private String name;
    private final Collection<? extends GrantedAuthority> authorities;
}
