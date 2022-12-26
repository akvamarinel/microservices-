package org.itmo.highload.usersservice.admin.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.itmo.highload.usersservice.admin.dto.AdminDto;
import org.itmo.highload.usersservice.admin.mapper.AdminMapper;
import org.itmo.highload.usersservice.admin.service.AdminService;
import org.itmo.highload.usersservice.delivery.controller.dto.DeliveryDto;
import org.itmo.highload.usersservice.delivery.model.Delivery;
import org.itmo.highload.usersservice.security.dto.AuthRequest;
import org.itmo.highload.usersservice.security.dto.AuthResponse;
import org.itmo.highload.usersservice.security.dto.RegistrationResponse;
import org.itmo.highload.usersservice.security.jwt.JwtProvider;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.model.UserRole;
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
@RequestMapping("admin")
public class AdminController {
    private final JwtProvider jwtProvider;
    private final UserDataService userDataService;
    private final AdminService adminService;

    private final AdminMapper adminMapper;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid AdminDto adminDto) {
        UserData userData = adminMapper.toModel(adminDto);
        if (adminService.create(userData) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(userData.getLogin(), "Registration success"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegistrationResponse(userData.getLogin(), "User with same login already exists"));
    }


    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authAdmin(@RequestBody @Valid AuthRequest request) {
        UserData user = userDataService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null, "Incorrect login or password"));
        } else {
            System.out.println(user.getRole() + " here");
            String token = jwtProvider.generateToken(user.getLogin());
            System.out.println(user.getId() + "айди");
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(user.getId(), token, "Authorization success"));
        }
    }

}

