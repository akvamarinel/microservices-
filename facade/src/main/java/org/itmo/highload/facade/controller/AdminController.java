package org.itmo.highload.facade.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.feignclient.UserFeignClient;
import org.itmo.highload.facade.security.dto.AuthRequest;
import org.itmo.highload.facade.security.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserFeignClient customerFeignClient;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        return customerFeignClient.authAdmin(authRequest);

    }

}
