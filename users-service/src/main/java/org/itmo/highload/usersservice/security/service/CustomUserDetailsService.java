package org.itmo.highload.usersservice.security.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.security.jwt.CustomUserDetails;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDataService userDataService;


    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserData user = userDataService.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + s + " not found");
        }
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}