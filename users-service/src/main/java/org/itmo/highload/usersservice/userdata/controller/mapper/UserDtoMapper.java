package org.itmo.highload.usersservice.userdata.controller.mapper;

import org.itmo.highload.usersservice.userdata.controller.dto.RoleDto;
import org.itmo.highload.usersservice.userdata.controller.dto.UserDataDto;
import org.itmo.highload.usersservice.userdata.model.UserData;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDataDto toDto(UserData userData) {
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setId(userData.getId());
        userDataDto.setName(userData.getName());
        userDataDto.setSurname(userData.getSurname());
        userDataDto.setLogin(userData.getLogin());
        userDataDto.setPassword(userData.getPassword());
        userDataDto.setRole(new RoleDto(userData.getRole().toString()));
        return userDataDto;

    }
}
