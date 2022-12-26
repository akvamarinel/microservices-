package org.itmo.highload.usersservice.customer.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    private String surname;

    @NotNull
    @NotBlank
    @NotEmpty
    private String login;

    @NotBlank
    @NotNull
    @Length(min = 5)
    private String password;

    @NotNull
    @NotBlank
    @NotEmpty
    private String address;
}
