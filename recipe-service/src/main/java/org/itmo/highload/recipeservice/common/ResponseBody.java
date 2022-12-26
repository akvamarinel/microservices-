package org.itmo.highload.recipeservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@ToString
public class ResponseBody {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String error;
    private String path;

}
