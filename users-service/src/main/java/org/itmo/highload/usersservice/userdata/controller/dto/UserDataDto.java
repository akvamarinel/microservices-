package org.itmo.highload.usersservice.userdata.controller.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {
    private UUID id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private RoleDto role;
}
