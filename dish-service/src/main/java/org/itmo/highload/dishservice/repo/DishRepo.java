package org.itmo.highload.dishservice.repo;

import org.itmo.highload.dishservice.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DishRepo extends JpaRepository<Dish, UUID> {
}
