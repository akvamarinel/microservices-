package org.itmo.highload.dishservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.dishservice.controller.dto.DishDto;
import org.itmo.highload.dishservice.controller.dto.RecipeDto;
import org.itmo.highload.dishservice.controller.mapper.DishMapper;
import org.itmo.highload.dishservice.model.Dish;
import org.itmo.highload.dishservice.repo.DishRepo;
import org.itmo.highload.dishservice.exception.EntityNotFoundException;
import org.itmo.highload.dishservice.service.feignclient.CategoryFeignClient;
import org.itmo.highload.dishservice.service.feignclient.RecipeFeignClient;
import org.itmo.highload.dishservice.service.feignclient.RestaurantFeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DishService {
    private final DishRepo dishRepo;
    private final DishMapper dishMapper;
    private final RecipeFeignClient recipeFeignClient;
    private final CategoryFeignClient categoryFeignClient;
    private final RestaurantFeignClient restaurantFeignClient;


    public Mono<Dish> getOne(UUID id) {
        return Mono.fromCallable(()-> dishRepo.findById(id))
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(Dish.class, id)));
    }

    public Flux<Dish> getAll() {
        return Flux.fromIterable(dishRepo.findAll());
    }

    @Transactional
    public Mono<Dish> create(DishDto dishDto) {
        return Mono.fromCallable(()-> {
            Dish dish = dishMapper.toModel(dishDto);
            restaurantFeignClient.getOne(dishDto.getRestaurantId());
            categoryFeignClient.getOne(dishDto.getId());
            recipeFeignClient.create(dishDto.getRecipe());
            return dishRepo.save(dish);
        }).subscribeOn(Schedulers.boundedElastic());

    }
}

