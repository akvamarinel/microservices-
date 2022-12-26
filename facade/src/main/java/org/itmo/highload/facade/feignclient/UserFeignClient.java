package org.itmo.highload.facade.feignclient;

import org.itmo.highload.facade.dto.CustomerDto;
import org.itmo.highload.facade.security.dto.AuthRequest;
import org.itmo.highload.facade.security.dto.AuthResponse;
import org.itmo.highload.facade.security.dto.RegistrationResponse;
import org.itmo.highload.facade.security.dto.UserDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "users-service")
public interface UserFeignClient {

    @PostMapping("customer/register")
    ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid CustomerDto customerDto);

    @PostMapping("customer/auth")
    ResponseEntity<AuthResponse> authCustomer(@RequestBody @Valid AuthRequest request);

    @GetMapping("userdata/{login}")
    ResponseEntity<UserDataDto> findByLogin(@PathVariable String login);

    @GetMapping("admin/auth")
    ResponseEntity<AuthResponse> authAdmin(@RequestBody @Valid AuthRequest request);
}
