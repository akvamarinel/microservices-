package org.itmo.highload.recipeservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "recipe")
public class Recipe {

    @Id
    private UUID id;

    private String descr;

//    @OneToOne(mappedBy = "recipe")
//    private Dish dish;
//
//    @OneToMany(mappedBy = "recipe")
//    private List<FoodInRecipe> foodInRecipe = new ArrayList<>();
}
