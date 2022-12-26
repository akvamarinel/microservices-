package org.itmo.highload.orderservice.repo;

import org.itmo.highload.orderservice.model.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserOrderRepo extends JpaRepository<UserOrder, UUID> {
    Page<UserOrder> getUserOrdersById(UUID id, Pageable pageable);
}
