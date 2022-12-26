package org.itmo.highload.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.orderservice.model.DishInOrder;
import org.itmo.highload.orderservice.repo.DishInOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DishInOrderService {
    private final DishInOrderRepo dishInOrderRepo;

    public DishInOrder create(UUID userOrder, UUID dish, int count){
        return dishInOrderRepo.save(new DishInOrder(UUID.randomUUID(), userOrder, dish, count));
    }

    public List<DishInOrder> getAllById(UUID userOrder) {
        return dishInOrderRepo.findAllByUserOrderId(userOrder);
    }
}
