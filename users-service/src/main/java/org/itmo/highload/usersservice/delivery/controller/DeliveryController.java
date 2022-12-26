package org.itmo.highload.usersservice.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.delivery.controller.dto.DeliveryDto;
import org.itmo.highload.usersservice.delivery.controller.mapper.DeliveryMapper;
import org.itmo.highload.usersservice.delivery.model.Delivery;
import org.itmo.highload.usersservice.delivery.service.DeliveryService;
import org.itmo.highload.usersservice.security.dto.AuthRequest;
import org.itmo.highload.usersservice.security.dto.AuthResponse;
import org.itmo.highload.usersservice.security.dto.RegistrationResponse;
import org.itmo.highload.usersservice.security.jwt.JwtProvider;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController()
@RequestMapping("delivery")
public class DeliveryController {
    private final JwtProvider jwtProvider;
    private final DeliveryService deliveryService;
    private final UserDataService userDataService;
    private final DeliveryMapper mapper;
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid DeliveryDto deliveryDto) {
        Delivery delivery = mapper.toModel(deliveryDto);
        UserData userData = delivery.getUserData();
        if (deliveryService.create(delivery) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(userData.getLogin(), "Registration success"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegistrationResponse(userData.getLogin(), "User with same login already exists"));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest request) {
        UserData user = userDataService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null,"Incorrect login or password" ));
        }
        String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(user.getId(), token, "Authorization success"));
    }
}
