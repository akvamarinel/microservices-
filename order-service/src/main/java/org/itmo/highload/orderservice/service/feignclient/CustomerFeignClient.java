package org.itmo.highload.orderservice.service.feignclient;

import org.itmo.highload.orderservice.controller.dto.CustomerDto;
import org.itmo.highload.orderservice.controller.dto.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="users-service")
public interface CustomerFeignClient {

    @PostMapping("/userdata/token")
    ResponseEntity<CustomerDto> findByToken(@RequestBody TokenDto tokenDto);

}
