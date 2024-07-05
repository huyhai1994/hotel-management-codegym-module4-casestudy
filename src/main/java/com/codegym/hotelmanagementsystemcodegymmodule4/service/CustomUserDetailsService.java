package com.codegym.hotelmanagementsystemcodegymmodule4.service;

import com.codegym.hotelmanagementsystemcodegymmodule4.exception.OurException;
import com.codegym.hotelmanagementsystemcodegymmodule4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(username).orElseThrow(() -> new OurException("Username/Email not Found"));
    }
}
