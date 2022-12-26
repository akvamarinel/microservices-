package org.itmo.highload.recipeservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeDto {
    private UUID id;

    @NotEmpty
    @NotBlank
    @NotEmpty
    private String descr;


}
