package org.itmo.highload.orderservice.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.orderservice.controller.dto.DishDto;
import org.itmo.highload.orderservice.controller.dto.UserOrderDto;
import org.itmo.highload.orderservice.model.UserOrder;
import org.itmo.highload.orderservice.service.DishInOrderService;
import org.itmo.highload.orderservice.service.feignclient.DishFeignClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserOrderMapper {

    private final DishFeignClient dishFeignClient;
    private final DishInOrderService dishInOrderService;
    //private final CustomerService customerService;

    public UserOrder toModel(UserOrderDto userOrderDto) {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(UUID.randomUUID());
        userOrder.setOrderTime(userOrderDto.getOrderTime());
        userOrder.setCustomerId(userOrder.getId());
       // userOrder.setCustomer(customerService.getById(userOrderDto.getCustomerId()));
        return userOrder;
    }

    public UserOrderDto toDto(UserOrder userOrder) {
        UserOrderDto userOrderDto = new UserOrderDto();
        userOrderDto.setId(userOrder.getId());
        userOrderDto.setOrderTime(userOrder.getOrderTime());
        userOrderDto.setCustomerId(userOrder.getCustomerId());
        Map<Integer, DishDto> dishes = new HashMap<>();
        dishInOrderService.getAllById(userOrder.getId()).forEach(dishInOrder -> dishes.put(dishInOrder.getCount(), new DishDto(dishInOrder.getDishId())));
        userOrderDto.setDishes(dishes);
        return userOrderDto;
    }
}
