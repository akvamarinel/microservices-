package org.itmo.highload.usersservice.security.jwt;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
    private final String login;
    private final String password;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public static CustomUserDetails fromUserToCustomUserDetails(UserData user) {
        return CustomUserDetails.builder().
                login(user.getLogin()).
                password(user.getPassword()).
                grantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))).
                build();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}