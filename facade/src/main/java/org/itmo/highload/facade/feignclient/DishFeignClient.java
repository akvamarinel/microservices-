package org.itmo.highload.facade.feignclient;

import org.itmo.highload.facade.security.dto.DishDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "dish-service")
public interface DishFeignClient {

    @GetMapping("/dishes/{id}")
    ResponseEntity<DishDto> getOne(@PathVariable UUID id);

    @PostMapping("/dishes")
    ResponseEntity<DishDto> create(@RequestBody DishDto dishDto);
}
