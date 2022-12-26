package org.itmo.highload.recipeservice.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.recipeservice.controller.dto.RecipeDto;
import org.itmo.highload.recipeservice.controller.mapper.RecipeMapper;
import org.itmo.highload.recipeservice.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController()
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @GetMapping("/{id}")
    Mono<ResponseEntity<RecipeDto>> getOne(@PathVariable UUID id) {
        return recipeService.getOne(id)
               .map(recipeMapper::toDto)
               .map(recipeDto -> ResponseEntity.status(HttpStatus.OK).body(recipeDto));
    }

    @GetMapping()
    Flux<RecipeDto> getAll() {
        return recipeService.getAll().map(recipeMapper::toDto);
    }

    @PostMapping()
    Mono<ResponseEntity<RecipeDto>> create(@RequestBody @Valid RecipeDto recipeDto) {
        return recipeService.create(recipeMapper.toModel(recipeDto))
                .map(recipeMapper::toDto)
                .map(recipeDto1 -> ResponseEntity.status(HttpStatus.CREATED).body(recipeDto1));
    }

//    @GetMapping("/recipes")
//    ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
//        List<FoodInRecipeDto> foodInRecipeDtoList = foodInRecipeService.getAll(pageable).stream()
//                .map(foodInRecipeMapper::toDto).collect(Collectors.toList());
//        boolean tmp = foodstuffService.getAll(pageable).hasNext();
//        ResponseEntity.BodyBuilder bodyBuilder = tmp ? ResponseEntity.status(HttpStatus.PARTIAL_CONTENT) : ResponseEntity.status(HttpStatus.OK);
//        return bodyBuilder.body(new ResponsePage(foodInRecipeDtoList, tmp));
//    }

    @PutMapping("{id}")
    Mono<ResponseEntity<RecipeDto>> update(@PathVariable UUID id, @Valid @RequestBody RecipeDto recipeDto) {
        return recipeService.update(id, recipeDto)
                .map(recipeMapper::toDto)
                .map(recipeDTO -> ResponseEntity.ok(recipeDTO));
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> deleteById(@PathVariable UUID id) {
        recipeService.delete(id);
        return Mono.just(ResponseEntity.ok().build());
    }
}


//    @GetMapping("/{id}")
//    ResponseEntity<?> getFoodInRecipe(@PathVariable UUID id, @PageableDefault Pageable pageable) {
//        List<RecipeDto> recipeDtoList = recipeService.getById(id, pageable).stream()
//                .map(foodInRecipeMapper::toDto).collect(Collectors.toList());
//        if (foodInRecipeDtoList.isEmpty()) {
//            throw new RuntimeException("Bad request");
//        }
//        boolean tmp = foodInRecipeService.getByRecipeId(id, pageable).hasNext();
//        ResponseEntity.BodyBuilder bodyBuilder = tmp ? ResponseEntity.status(HttpStatus.PARTIAL_CONTENT) : ResponseEntity.status(HttpStatus.OK);
//        return bodyBuilder.body(new ResponsePage(foodInRecipeDtoList, tmp));
//
//    }

//    @PutMapping("/{id}")
//    ResponseEntity<FoodInRecipeDto> addFoodInRecipe(@PathVariable UUID id, @Valid @RequestBody FoodInRecipeDto foodInRecipeDto) {
//        Recipe recipe = recipeService.getOne(id);
//        Foodstuff foodstuff = foodstuffService.getOne(foodInRecipeDto.getFoodstuffId());
//        FoodInRecipe foodInRecipe = FoodInRecipe.builder()
//                .foodstuff(foodstuff)
//                .recipe(recipe)
//                .weight(foodInRecipeDto.getWeight())
//                .id(new FoodInRecipeKey(foodstuff.getId(), recipe.getId()))
//                .build();
//        return ResponseEntity.ok(foodInRecipeMapper.toDto(foodInRecipeService.create(foodInRecipe)));
//    }

//    @DeleteMapping("/{recipeId}/food_in_recipe/{foodstuffId}")
//    ResponseEntity<Void> deleteFoodInRecipe(@PathVariable UUID recipeId, @PathVariable UUID foodstuffId) {
//        FoodInRecipeKey key = new FoodInRecipeKey(foodstuffId, recipeId);
//        foodInRecipeService.deleteById(key);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
