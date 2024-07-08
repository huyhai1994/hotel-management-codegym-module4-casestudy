package com.codegym.hotelmanagementsystemcodegymmodule4.config.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public boolean validateJwtToken(String jwt) {
        return false;
    }

    public String getUsernameFromJwtToken(String jwt) {
        return null;
    }
}
