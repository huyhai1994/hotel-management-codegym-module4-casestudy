package com.codegym.hotelmanagementsystemcodegymmodule4.config;

import com.codegym.hotelmanagementsystemcodegymmodule4.config.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*TODO: s-step6 creating security Config -> connect beans*/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /*TODO: this is like the contact between
     *       back end and security company*/
    @Autowired
    private UserServices userServices;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userServices);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().requestMatchers("/api/auth/login**").permitAll().requestMatchers("/api/role").permitAll().requestMatchers(HttpMethod.GET, "/api/customers**").authenticated().requestMatchers("/api/customers**").hasAnyAuthority("ROLE_ADMIN").requestMatchers(HttpMethod.PUT, "/api/customers**").hasAnyAuthority("ROLE_ADMIN").requestMatchers(HttpMethod.POST, "/api/customers**").hasAnyAuthority("ROLE_ADMIN").requestMatchers(HttpMethod.DELETE, "/api/customers/**").hasAnyAuthority("ROLE_ADMIN")).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
        /*TODO: session STATELESS -> not save state
         *       "each time an user authorized the security
         *       will double check again if the user want to
         *       re-entry*/
    }

}
