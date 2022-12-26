package org.itmo.highload.usersservice.userdata.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.repo.UserDataRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDataService {

    private final UserDataRepo userDataRepo;
    private final PasswordEncoder passwordEncoder;


    public Boolean existsByLogin(String login) {
        return userDataRepo.existsByLogin(login);
    }


    public UserData findByLogin(String login) {
        return userDataRepo.findByLogin(login);
    }

    public UserData findByLoginAndPassword(String login, String password) {
        UserData user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
