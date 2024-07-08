package com.codegym.hotelmanagementsystemcodegymmodule4.config;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String email;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final Collection<? extends GrantedAuthority> roles;

    public static UserDetails build(User byEmail) {
        return null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
