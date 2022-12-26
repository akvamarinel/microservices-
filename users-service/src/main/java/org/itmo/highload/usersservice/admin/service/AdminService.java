package org.itmo.highload.usersservice.admin.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.repo.UserDataRepo;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserDataService userDataService;
    private final UserDataRepo userDataRepo;
    private final PasswordEncoder passwordEncoder;

    public UserData create(UserData userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        if (!userDataService.existsByLogin(userData.getLogin())) {
            return userDataRepo.save(userData);
        }
        return null;
    }
}