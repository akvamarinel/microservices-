package org.itmo.highload.usersservice.userdata.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.customer.service.CustomerService;
import org.itmo.highload.usersservice.security.dto.AuthRequest;
import org.itmo.highload.usersservice.security.dto.AuthResponse;
import org.itmo.highload.usersservice.security.jwt.JwtProvider;
import org.itmo.highload.usersservice.userdata.controller.dto.TokenDto;
import org.itmo.highload.usersservice.userdata.controller.dto.UserDataDto;
import org.itmo.highload.usersservice.userdata.controller.dto.CustomerDto;
import org.itmo.highload.usersservice.userdata.controller.mapper.UserDtoMapper;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.service.UserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userdata")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;
    private final UserDtoMapper userDtoMapper;
    private final JwtProvider jwtProvider;
    private final CustomerService customerService;

    @GetMapping("/{login}")
    public ResponseEntity<UserDataDto> findByLogin(@PathVariable String login) {
        return ResponseEntity.ok(userDtoMapper.toDto(userDataService.findByLogin(login)));
    }

    @GetMapping
    public ResponseEntity<AuthResponse> findByLoginAndPassword(@RequestBody AuthRequest authRequest) {
        UserData userData = userDataService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        if (userData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null,null, "Incorrect login or password"));
        }
        String token = jwtProvider.generateToken(userData.getLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(userData.getId(), token, "Authorization success"));
    }

    @PostMapping("/token")
    public ResponseEntity<CustomerDto> findByToken(@RequestBody TokenDto token) {
        System.out.println(token.getToken() + "  here");
        String tkn = token.getToken();
        CustomerDto dto = new CustomerDto(customerService.getByUserDataId(userDataService.findByLogin(jwtProvider.getLoginFromToken(tkn)).getId()).getId());
        return ResponseEntity.ok(dto);
    }
}
