package org.itmo.highload.dishservice.service.feignclient;

import org.itmo.highload.dishservice.controller.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "restaurant-service")
public interface RestaurantFeignClient {

    @GetMapping("/restaurants/{id}")
    RestaurantDto getOne(@PathVariable UUID id);

}
