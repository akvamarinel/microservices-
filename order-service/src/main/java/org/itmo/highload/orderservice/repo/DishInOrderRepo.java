package org.itmo.highload.orderservice.repo;

import org.itmo.highload.orderservice.model.DishInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DishInOrderRepo extends JpaRepository<DishInOrder, UUID> {

    List<DishInOrder> findAllByUserOrderId(UUID id);
}
