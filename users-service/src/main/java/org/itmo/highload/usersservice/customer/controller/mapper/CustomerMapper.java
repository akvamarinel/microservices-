package org.itmo.highload.usersservice.customer.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.customer.controller.dto.CustomerDto;
import org.itmo.highload.usersservice.customer.model.Customer;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerMapper {


    public Customer toModel(CustomerDto customerDto) {
        UserData userData = UserData.builder()
                .id(UUID.randomUUID())
                .login(customerDto.getLogin())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .role(UserRole.ROLE_CUSTOMER)
                .password(customerDto.getPassword())
                .build();

        return Customer.builder()
                .id(UUID.randomUUID())
                .userData(userData)
                .address(customerDto.getAddress())
                .build();
    }
}
