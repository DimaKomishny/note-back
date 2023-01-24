package com.service.api.note.security.model;

import com.service.api.note.entity.Status;
import com.service.api.note.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        var isActiveUser = user.getStatus().equals(Status.ACTIVE);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                isActiveUser,
                isActiveUser,
                isActiveUser,
                isActiveUser,
                user.getRole().getAuthorities());
    }
}
