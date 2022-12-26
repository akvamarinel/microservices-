package org.itmo.highload.recipeservice.controller.mapper;

import lombok.RequiredArgsConstructor;

import org.itmo.highload.recipeservice.controller.dto.RecipeDto;
import org.itmo.highload.recipeservice.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.UUID;



@Component
@RequiredArgsConstructor
public class RecipeMapper {

    public RecipeDto toDto(Recipe recipe) {
        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setDescr(recipe.getDescr());
        return dto;
    }

    public Recipe toModel(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setId(UUID.randomUUID());
        recipe.setDescr(dto.getDescr());
        return recipe;
    }
}
