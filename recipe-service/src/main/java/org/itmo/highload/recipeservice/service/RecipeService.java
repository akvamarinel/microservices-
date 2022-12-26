package org.itmo.highload.recipeservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.recipeservice.controller.dto.RecipeDto;
import org.itmo.highload.recipeservice.controller.mapper.RecipeMapper;
import org.itmo.highload.recipeservice.model.Recipe;
import org.itmo.highload.recipeservice.repo.RecipeRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public Mono<Recipe> getOne(UUID id) {
        return recipeRepo.findById(id);
    }

    public Mono<Recipe> create(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    public Flux<Recipe> getAll() {
        return recipeRepo.findAll();
    }


    public Mono<Recipe> update(UUID id, RecipeDto recipeDto) {
        return getOne(id)
                .doOnNext(recipe -> {
                    recipeRepo.delete(recipe);
                    recipe.setDescr(recipeDto.getDescr());
                    recipeRepo.save(recipe);
                });
    }

    public Mono<Void> delete(UUID id) {
        getOne(id).doOnNext(recipe -> recipeRepo.delete(recipe));
        return Mono.empty();
    }

}
