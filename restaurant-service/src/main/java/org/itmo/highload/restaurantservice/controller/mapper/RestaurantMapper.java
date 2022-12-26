package org.itmo.highload.restaurantservice.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.restaurantservice.controller.dto.RestaurantDto;
import org.itmo.highload.restaurantservice.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RestaurantMapper {


    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantResponseDto = new RestaurantDto();
        restaurantResponseDto.setName(restaurant.getName());
        restaurantResponseDto.setId(restaurant.getId());
        restaurantResponseDto.setRating(restaurant.getRating());
        return restaurantResponseDto;
    }

    public Restaurant toModel(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(UUID.randomUUID());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setName(restaurantDto.getName());
        return restaurant;
    }
}
