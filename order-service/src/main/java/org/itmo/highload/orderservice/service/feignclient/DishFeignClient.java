package org.itmo.highload.orderservice.service.feignclient;

import org.itmo.highload.orderservice.controller.dto.DishDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="dish-service")
public interface DishFeignClient {

    @GetMapping("/dishes/{id}")
    ResponseEntity<DishDto> getOne(@PathVariable UUID id);
}


