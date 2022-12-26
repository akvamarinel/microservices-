package org.itmo.highload.categoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.categoryservice.common.ResponsePage;
import org.itmo.highload.categoryservice.controller.dto.CategoryDto;
import org.itmo.highload.categoryservice.controller.mapper.CategoryMapper;
import org.itmo.highload.categoryservice.service.CategoryService;
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
@RequestMapping("categories")
public class CategoryController {
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @PostMapping()
    public Mono <ResponseEntity<CategoryDto>> create(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.create(categoryMapper.toModel(categoryDto))
                .map(categoryMapper::toDto)
                .map(restaurantDTO -> ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTO));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CategoryDto>> getOne(@PathVariable UUID id) {
        return categoryService.getOne(id)
                .map(categoryMapper::toDto)
                .map(restaurantDto -> ResponseEntity.status(HttpStatus.OK).body(restaurantDto));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID id) {
        return categoryService.delete(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping()
    public Mono<ResponseEntity<Flux<CategoryDto>>> getAll(@PageableDefault Pageable pageable) {
        return Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll(pageable)
                .map(categoryMapper::toDto)));
    }

    @PostMapping("/{id}")
    public Mono<ResponseEntity<CategoryDto>> update(@PathVariable UUID id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto)
                .map(categoryMapper::toDto)
                .map(restaurantDTO -> ResponseEntity.status(HttpStatus.OK).body(restaurantDTO));
    }
}
