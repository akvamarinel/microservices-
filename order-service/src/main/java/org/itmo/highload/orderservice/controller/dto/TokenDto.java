package org.itmo.highload.orderservice.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
}
