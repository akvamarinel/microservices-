package org.itmo.highload.categoryservice.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private UUID id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
}
