package com.codegym.hotelmanagementsystemcodegymmodule4.config;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import com.codegym.hotelmanagementsystemcodegymmodule4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*TODO s-step2: implement the UserDetailsService (after import the security librery)*/
@AllArgsConstructor
@Service
public class UserServices implements UserDetailsService {

    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserPrincipal.build(userRepository.findByEmail(email));
    }
}
