package org.itmo.highload.facade.feignclient;

import org.itmo.highload.facade.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(name = "restaurant-service")
public interface RestaurantFeignClient {

    @PostMapping("/restaurants")
    ResponseEntity<RestaurantDto> create(@RequestBody @Valid RestaurantDto restaurantDto);

    @GetMapping("/restaurants/{id}")
    ResponseEntity<RestaurantDto> getOne(@PathVariable UUID id);

    @PutMapping("/restaurants/{id}")
    ResponseEntity<RestaurantDto> update(@PathVariable UUID id, @RequestBody @Valid RestaurantDto restaurantDto);

//    @GetMapping("/restaurants")
//    ResponseEntity<RestaurantDto> getAll(@PageableDefault Pageable pageable);
   }
