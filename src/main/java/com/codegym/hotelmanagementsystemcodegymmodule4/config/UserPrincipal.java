package com.codegym.hotelmanagementsystemcodegymmodule4.config;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import com.codegym.hotelmanagementsystemcodegymmodule4.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public static UserDetails build(User user) {
             /*TODO: Chuyen role (dang chuoi) thanh
                grantedAuthority (dang co the
                cap quyen cho user duoc)*/
        {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new UserPrincipal(user.getEmail(),
                    user.getPassword(), authorities);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
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
