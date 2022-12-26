package org.itmo.highload.categoryservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.categoryservice.controller.dto.CategoryDto;
import org.itmo.highload.categoryservice.exception.EntityNotFoundException;
import org.itmo.highload.categoryservice.model.Category;
import org.itmo.highload.categoryservice.repo.CategoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;


    public Mono<Category> create(Category category) {
        category.setId(UUID.randomUUID());
        return Mono.just(categoryRepo.save(category));
    }

    public Mono <Void> delete(UUID id) {
        getOne(id).doOnNext(category -> categoryRepo.deleteById(category.getId()));
        return Mono.empty();
    }

    public Mono <Category> getOne(UUID id) {
        return Mono.fromCallable(()-> categoryRepo.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(Category.class, id)));
    }

    public Flux<Category> getAll(Pageable pageable) {
        return Mono.fromCallable(() ->categoryRepo.findAll(pageable).stream())
                .subscribeOn(Schedulers.boundedElastic())
                .flux()
                .flatMap(Flux::fromStream);
    }

    public Mono<Category> update(UUID id, CategoryDto categoryDto) {
       return Mono.just(categoryRepo.update(id, new Category(id, categoryDto.getName())));
    }

}
