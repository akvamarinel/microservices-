package org.itmo.highload.usersservice.admin.mapper;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.usersservice.admin.dto.AdminDto;
import org.itmo.highload.usersservice.delivery.controller.dto.DeliveryDto;
import org.itmo.highload.usersservice.delivery.model.Delivery;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.itmo.highload.usersservice.userdata.model.UserRole;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AdminMapper {
    public UserData toModel(AdminDto adminDto) {
      UserData userData = new UserData();
      userData.setId(UUID.randomUUID());
      userData.setName(adminDto.getName());
      userData.setSurname(adminDto.getSurname());
      userData.setLogin(adminDto.getLogin());
      userData.setPassword(adminDto.getPassword());
      userData.setRole(UserRole.ROLE_ADMIN);
      return userData;
    }
}
