package org.itmo.highload.facade.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.dto.RestaurantDto;
import org.itmo.highload.facade.feignclient.RestaurantFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("restaurants")
public class RestaurantController {
    private final RestaurantFeignClient restaurantFeignClient;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<RestaurantDto> create(@RequestBody @Valid RestaurantDto restaurantDto) {
        return restaurantFeignClient.create(restaurantDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getOne(@PathVariable UUID id) {
        return restaurantFeignClient.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable UUID id, @RequestBody @Valid RestaurantDto restaurantDto) {
        return restaurantFeignClient.update(id, restaurantDto);
    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
//    @GetMapping("/restaurants")
//    public ResponseEntity<RestaurantDto> getAll(@PageableDefault Pageable pageable) {
//        return restaurantFeignClient.getAll(pageable);
//    }
}
