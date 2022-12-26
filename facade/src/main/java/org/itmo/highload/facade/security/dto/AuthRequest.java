package org.itmo.highload.facade.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthRequest {
    private UUID id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}