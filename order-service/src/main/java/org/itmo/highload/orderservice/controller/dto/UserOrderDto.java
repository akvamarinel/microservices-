package org.itmo.highload.orderservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto {
    private UUID id;

    private Date orderTime;

    private UUID customerId;
    //private UUID deliveryId;

    @NotNull
    private Map<Integer, DishDto> dishes;

}
