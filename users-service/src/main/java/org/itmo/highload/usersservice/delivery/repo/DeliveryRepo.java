package org.itmo.highload.usersservice.delivery.repo;

import org.itmo.highload.usersservice.delivery.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryRepo extends CrudRepository<Delivery, UUID> {
}
