package org.itmo.highload.facade.security.jwt;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.feignclient.UserFeignClient;
import org.itmo.highload.facade.security.dto.UserDataDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserFeignClient customerFeignClient;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDataDto userDataDto = customerFeignClient.findByLogin(s).getBody();
        System.out.println(userDataDto.getLogin() + "логин :)");
        if (userDataDto == null) {
            throw new UsernameNotFoundException("User with login " + s + " not found");
        }
        return User
                .withUsername(userDataDto.getLogin())
                .password(userDataDto.getPassword())
                .authorities(userDataDto.getRole().getRole())
                .build();
    }
}