package org.itmo.highload.dishservice.service.feignclient;

import org.itmo.highload.dishservice.controller.dto.RecipeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(name="recipe-service")
public interface RecipeFeignClient {

    @PostMapping("/recipes")
    RecipeDto create(@RequestBody @Valid RecipeDto recipeDto);

    @GetMapping("/recipes/{id}")
    RecipeDto getOne(@PathVariable UUID id);

}
