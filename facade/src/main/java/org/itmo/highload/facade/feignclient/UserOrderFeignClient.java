package org.itmo.highload.facade.feignclient;

import org.itmo.highload.facade.security.dto.UserOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name="userorder-service")
public interface UserOrderFeignClient {

    @PostMapping("userorder")
    ResponseEntity<UserOrderDto>create(@RequestBody UserOrderDto userOrderDto, @RequestHeader("UserID") UUID id);
}
