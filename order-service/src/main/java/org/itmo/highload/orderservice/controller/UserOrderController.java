package org.itmo.highload.orderservice.controller;

import feign.HeaderMap;
import lombok.RequiredArgsConstructor;
import org.itmo.highload.orderservice.controller.dto.UserOrderDto;
import org.itmo.highload.orderservice.controller.mapper.UserOrderMapper;
import org.itmo.highload.orderservice.service.UserOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@RestController()
@RequiredArgsConstructor
@RequestMapping("userorder")
public class UserOrderController {
    private final UserOrderService userOrderService;
    private final UserOrderMapper userOrderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserOrderDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(userOrderService.getOne(id));
    }

    @PostMapping()
    public ResponseEntity<UserOrderDto> create(@RequestBody @Valid UserOrderDto userOrderDto, @RequestHeader("UserID") UUID id) {
        System.out.println(id + " тут есть айди");
        return ResponseEntity.status(HttpStatus.CREATED).body(userOrderMapper.toDto(userOrderService.create(userOrderDto, id)));
    }

}
