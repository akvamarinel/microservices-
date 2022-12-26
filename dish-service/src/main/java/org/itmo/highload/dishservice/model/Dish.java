package org.itmo.highload.dishservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "recipe_id")
    private UUID recipeId;

    @Column(name="restaurant_id")
    private UUID restaurantId;

    @Column(name="category_id")
    private UUID categoryId;
}
