package org.itmo.highload.facade.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.dto.CustomerDto;
import org.itmo.highload.facade.feignclient.UserFeignClient;
import org.itmo.highload.facade.security.dto.AuthRequest;
import org.itmo.highload.facade.security.dto.AuthResponse;
import org.itmo.highload.facade.security.dto.RegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
    private final UserFeignClient customerFeignClient;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid CustomerDto customerDto) {
        return customerFeignClient.registerUser(customerDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authCustomer(@RequestBody @Valid AuthRequest request) {
        return customerFeignClient.authCustomer(request);
    }
}
