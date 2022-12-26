package org.itmo.highload.recipeservice.repo;


import org.itmo.highload.recipeservice.model.Recipe;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface RecipeRepo extends R2dbcRepository<Recipe, UUID> {
}

