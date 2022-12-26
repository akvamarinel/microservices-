package org.itmo.highload.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.orderservice.controller.dto.DishDto;
import org.itmo.highload.orderservice.controller.dto.TokenDto;
import org.itmo.highload.orderservice.controller.dto.UserOrderDto;
import org.itmo.highload.orderservice.controller.mapper.UserOrderMapper;
import org.itmo.highload.orderservice.exception.EntityNotFoundException;
import org.itmo.highload.orderservice.model.DishInOrder;
import org.itmo.highload.orderservice.model.UserOrder;
import org.itmo.highload.orderservice.repo.UserOrderRepo;
import org.itmo.highload.orderservice.service.feignclient.CustomerFeignClient;
import org.itmo.highload.orderservice.service.feignclient.DishFeignClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserOrderService {

    private final UserOrderRepo userOrderRepo;
    private final DishFeignClient dishFeignClient;
    private final UserOrderMapper userOrderMapper;
    private final DishInOrderService dishInOrderService;
    private final CustomerFeignClient customerFeignClient;
//    private final UserDataService userDataService;
//    private final CustomerService customerService;



    @Transactional
    public UserOrder create(UserOrderDto userOrderDto, UUID id) {
        UserOrder userOrder = userOrderMapper.toModel(userOrderDto);
        userOrderDto.getDishes().forEach((integer, dishDto) -> dishFeignClient.getOne(dishDto.getId()));
        userOrder.setCustomerId(id);

//        UserData userData = userDataService.findByLogin(tmp);
//        Customer customer = customerService.getByUserDataId(userData.getId());
//        userOrder.setCustomer(customer);
        userOrder.setId(UUID.randomUUID());
        userOrderDto.getDishes().forEach(((integer, dishDto) -> dishInOrderService.create(userOrder.getId(), dishDto.getId(), integer)));
        userOrder.setOrderTime(new Date());
        return userOrderRepo.save(userOrder);
    }
    @Transactional
    public UserOrderDto getOne(UUID id) {
        UserOrder userOrder = userOrderRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(UserOrder.class, id));
        UserOrderDto userOrderDto = userOrderMapper.toDto(userOrder);
        List<DishInOrder> dishInOrderList = dishInOrderService.getAllById(id);
        Map<Integer, DishDto> map = new HashMap<>();
        dishInOrderList.forEach(dishInOrder ->  map.put(dishInOrder.getCount(), new DishDto(dishInOrder.getDishId())));
        userOrderDto.setDishes(map);
        return userOrderDto;
    }

//    public Page<UserOrder> getAll(Principal principal, Pageable pageable) {
//        var tmp = principal.getName();
//        //UserData userData = userDataService.findByLogin(tmp);
//        return userOrderRepo.getUserOrdersById(userData.getId(), pageable);
//    }

    public void delete(UserOrder userOrder) {
        userOrderRepo.deleteById(userOrder.getId());
    }

    public UserOrder update(UUID id, UserOrder userOrder) {
        return null;
    }

}
