package org.itmo.highload.restaurantservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.restaurantservice.exception.EntityNotFoundException;
import org.itmo.highload.restaurantservice.controller.dto.RestaurantDto;
import org.itmo.highload.restaurantservice.model.Restaurant;
import org.itmo.highload.restaurantservice.repo.RestaurantRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;

    public Mono<Restaurant> create(Restaurant restaurant) {
        return Mono.fromCallable(() -> restaurantRepo.save(restaurant))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Restaurant> getOne(UUID id) {
        return Mono.fromCallable(() -> restaurantRepo.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(Restaurant.class, id)));
    }

    public Flux<Restaurant> getAll(Pageable pageable) {
        return Mono.fromCallable(() -> restaurantRepo.findAll(pageable).stream())
                .subscribeOn(Schedulers.boundedElastic())
                .flux()
                .flatMap(Flux::fromStream);
    }


    public Mono<Void> delete(UUID id) {
        return Mono.fromCallable(() -> restaurantRepo.findById(id))
                .map(Optional::get)
                .doOnNext(restaurant -> restaurantRepo.deleteById(restaurant.getId()))
                .then(Mono.empty());
    }

    public Mono<Restaurant> update(UUID id, RestaurantDto restaurantDto) {
        return getOne(id).
                doOnNext(restaurant -> {
                    restaurant.setName(restaurantDto.getName());
                    restaurant.setRating(restaurantDto.getRating());
                })
                .map(restaurantRepo::save);
    }
}
