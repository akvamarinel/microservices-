package org.itmo.highload.dishservice.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.dishservice.controller.dto.CategoryDto;
import org.itmo.highload.dishservice.controller.dto.DishDto;
import org.itmo.highload.dishservice.controller.dto.RecipeDto;
import org.itmo.highload.dishservice.model.Dish;
import org.itmo.highload.dishservice.service.feignclient.CategoryFeignClient;
import org.itmo.highload.dishservice.service.feignclient.RecipeFeignClient;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RecipeFeignClient recipeFeignClient;

    public DishDto toDto(Dish dish) { //fixme builder
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
      RecipeDto recipeDto = recipeFeignClient.getOne(dish.getRecipeId());
      dishDto.setRecipe(recipeDto);

        dishDto.setCategoryId(dish.getCategoryId());
        dishDto.setRestaurantId(dish.getRestaurantId());
        return dishDto;
    }

    public Dish toModel(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setId(UUID.randomUUID());
        dish.setName(dishDto.getName());
        //dish.setRecipeId(dishDto.getRecipeDto().getId());
        dish.setRestaurantId(dishDto.getRestaurantId());
        dish.setCategoryId(dishDto.getCategoryId());
        return dish;
    }
}

