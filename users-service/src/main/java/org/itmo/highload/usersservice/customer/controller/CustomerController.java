package org.itmo.highload.usersservice.customer.controller;


import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.common.ResponsePage;
import org.itmo.highload.usersservice.customer.controller.dto.CustomerDto;
import org.itmo.highload.usersservice.customer.controller.mapper.CustomerMapper;
import org.itmo.highload.usersservice.customer.model.Customer;
import org.itmo.highload.usersservice.customer.service.CustomerService;
import org.itmo.highload.usersservice.security.dto.AuthRequest;
import org.itmo.highload.usersservice.security.dto.AuthResponse;
import org.itmo.highload.usersservice.security.dto.RegistrationResponse;
import org.itmo.highload.usersservice.security.jwt.JwtProvider;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
//import org.itmo.highload.userorder.controller.dto.UserOrderDto;
//import org.itmo.highload.userorder.controller.mapper.UserOrderMapper;
//import org.itmo.highload.userorder.service.UserOrderService;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final JwtProvider jwtProvider;
    private final CustomerService customerService;
    private final UserDataService userDataService;
    private final CustomerMapper customerMapper;

//    private final UserOrderMapper userOrderMapper;
//    private final UserOrderService userOrderService;


    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid CustomerDto customerDto) {
        Customer customer = customerMapper.toModel(customerDto);
        UserData userData = customer.getUserData();
        if (customerService.create(customer) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(userData.getLogin(), "Registration success"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegistrationResponse(userData.getLogin(), "User with same login already exists"));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authCustomer(@RequestBody @Valid AuthRequest request) {
        System.out.println("in controller ");
        UserData user = userDataService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null) {
            System.out.println("in controller not user ");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null, "Incorrect login or password"));
        }

        String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(customerService.getByUserDataId(user.getId()).getId(),token, "Authorization success"));
    }

//    @PutMapping("/orders")
//    public ResponseEntity<UserOrderDto> createOrder(Principal principal, @RequestBody @Valid UserOrderDto userOrderDto) {
//        userOrderDto.setOrderTime(new Date());
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(userOrderMapper.toDto(userOrderService.create(principal, userOrderMapper.toModel(userOrderDto))));
//    }

//    @GetMapping("/orders")
//    public ResponseEntity<?> getAllOrders(Principal principal, @PageableDefault Pageable pageable) {
//        List<UserOrderDto> userOrderDtoList = userOrderService.getAll(principal, pageable).stream()
//                .map(userOrderMapper::toDto).collect(Collectors.toList());
//        boolean tmp = userOrderService.getAll(principal, pageable).hasNext();
//        ResponseEntity.BodyBuilder bodyBuilder = tmp ? ResponseEntity.status(HttpStatus.PARTIAL_CONTENT) : ResponseEntity.status(HttpStatus.OK);
//        return bodyBuilder.body(new ResponsePage(userOrderDtoList, tmp));
//    }

}
