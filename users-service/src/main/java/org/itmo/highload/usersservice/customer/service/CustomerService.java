package org.itmo.highload.usersservice.customer.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.customer.model.Customer;
import org.itmo.highload.usersservice.customer.repo.CustomerRepo;
import org.itmo.highload.usersservice.exception.EntityNotFoundException;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.repo.UserDataRepo;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDataService userDataService;
    private final UserDataRepo userDataRepo;
    public Customer create(Customer customer) {
        UserData userData = customer.getUserData();
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        if (!userDataService.existsByLogin(userData.getLogin())) {
            userDataRepo.save(userData);
            return customerRepo.save(customer);
        } else {
            return null;
        }
    }

    public Customer getById(UUID id) {
        return customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Customer.class, id));
    }

    public Customer getByUserDataId(UUID id) {
        return customerRepo.getCustomerByUserDataId(id);
    }
}
