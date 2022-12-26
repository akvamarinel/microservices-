package org.itmo.highload.facade.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private UUID id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    private RecipeDto recipe;

    private UUID restaurantId;

    private UUID categoryId;
}
