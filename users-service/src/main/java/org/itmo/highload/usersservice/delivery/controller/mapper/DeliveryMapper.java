package org.itmo.highload.usersservice.delivery.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.delivery.controller.dto.DeliveryDto;
import org.itmo.highload.usersservice.delivery.model.Delivery;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DeliveryMapper {
    public Delivery toModel(DeliveryDto deliveryDto) {
        UserData userData = UserData.builder()
                .id(UUID.randomUUID())
                .login(deliveryDto.getLogin())
                .name(deliveryDto.getName())
                .surname(deliveryDto.getSurname())
                .role(UserRole.ROLE_DELIVERY)
                .password(deliveryDto.getPassword())
                .build();
        return Delivery.builder()
                .id(UUID.randomUUID())
                .userData(userData)
                .build();
    }
}
