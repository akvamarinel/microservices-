package org.itmo.highload.facade.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.feignclient.UserOrderFeignClient;
import org.itmo.highload.facade.security.dto.UserOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("userorder")
@RequiredArgsConstructor
public class UserOrderController {

    private final UserOrderFeignClient userOrderFeignClient;

    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @PostMapping()
    public ResponseEntity<UserOrderDto> create(@RequestBody UserOrderDto userOrderDto, @RequestHeader("UserID") UUID id) {
        System.out.println("here " + userOrderDto.getDishes());
        return userOrderFeignClient.create(userOrderDto, id );
    }
}
