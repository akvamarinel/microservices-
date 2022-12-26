package org.itmo.highload.facade.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.feignclient.DishFeignClient;
import org.itmo.highload.facade.security.dto.DishDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishFeignClient dishFeignClient;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getOne(@PathVariable UUID id) {
        return dishFeignClient.getOne(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<DishDto> create(@RequestBody DishDto dishDto) {
        return dishFeignClient.create(dishDto);
    }
}
