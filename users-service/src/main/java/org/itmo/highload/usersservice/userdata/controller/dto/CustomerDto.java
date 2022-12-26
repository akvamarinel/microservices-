package org.itmo.highload.usersservice.userdata.controller.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID id;
}
