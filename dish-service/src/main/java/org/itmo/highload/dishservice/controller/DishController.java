package org.itmo.highload.dishservice.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.dishservice.common.ResponsePage;
import org.itmo.highload.dishservice.controller.dto.DishDto;
import org.itmo.highload.dishservice.controller.mapper.DishMapper;
import org.itmo.highload.dishservice.model.Dish;
import org.itmo.highload.dishservice.service.DishService;

import org.itmo.highload.dishservice.service.feignclient.RecipeFeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController()
@RequestMapping("dishes")
public class DishController {

    private final DishMapper dishMapper;
    private final DishService dishService;


    @GetMapping("/{id}")
    Mono<ResponseEntity<DishDto>> getOne(@PathVariable UUID id) {
        return dishService.getOne(id)
                .map(dishMapper::toDto)
                .map(ResponseEntity::ok);

    }

    @GetMapping()
    Flux<DishDto> getAll() {
        return dishService.getAll().map(dishMapper::toDto);
    }

    @PostMapping()
    Mono<ResponseEntity<DishDto>> create(@RequestBody @Valid DishDto dishDto) {
        return dishService.create(dishDto)
                .map(dishMapper::toDto)
                .map(dishDTO -> ResponseEntity.status(HttpStatus.CREATED).body(dishDTO));
    }

//    @PutMapping("/{id}")
//    ResponseEntity<RecipeDto> update(@PathVariable UUID id, @Valid @RequestBody RecipeDto recipeDto) {
//        return ResponseEntity.ok(recipeMapper.toDto(recipeService.update(id, recipeDto)));
//    }

//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> deleteById(@PathVariable UUID id) {
//        recipeService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
