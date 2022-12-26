package org.itmo.highload.restaurantservice.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.restaurantservice.common.ResponsePage;
import org.itmo.highload.restaurantservice.controller.dto.RestaurantDto;
import org.itmo.highload.restaurantservice.controller.mapper.RestaurantMapper;
import org.itmo.highload.restaurantservice.service.RestaurantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController()
@RequestMapping("restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<RestaurantDto>> getOne(@PathVariable UUID id) {
        return restaurantService.getOne(id)
                .map(restaurantMapper::toDto)
                .map(restaurantDto -> ResponseEntity.status(HttpStatus.OK).body(restaurantDto));
    }

    @PostMapping()
    public Mono<ResponseEntity<RestaurantDto>> create(@RequestBody @Valid RestaurantDto restaurantDto) {
        return restaurantService.create(restaurantMapper.toModel(restaurantDto))
                .map(restaurantMapper::toDto)
                .map(restaurantDTO -> ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTO));
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(restaurantMapper.toDto(restaurantService.create(restaurantMapper.toModel(restaurantDto))));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID id) {
        return restaurantService.delete(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping()
    public Mono<ResponseEntity<Flux<RestaurantDto>>> getAll(@PageableDefault Pageable pageable) {
        return Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAll(pageable)
                .map(restaurantMapper::toDto)));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<RestaurantDto>> update(@PathVariable UUID id, @RequestBody @Valid RestaurantDto restaurantDto) {
        return restaurantService.update(id, restaurantDto)
                .map(restaurantMapper::toDto)
                .map(restaurantDTO -> ResponseEntity.status(HttpStatus.OK).body(restaurantDTO));
    }
}
