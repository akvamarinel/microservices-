package org.itmo.highload.restaurantservice.repo;

import org.itmo.highload.restaurantservice.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RestaurantRepo extends JpaRepository<Restaurant, UUID> {
}
