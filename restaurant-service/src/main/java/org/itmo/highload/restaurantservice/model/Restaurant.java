package org.itmo.highload.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private Integer rating;

//    @OneToMany(mappedBy = "restaurant")
//    private List<Dish> dishes = new ArrayList<>();
}
