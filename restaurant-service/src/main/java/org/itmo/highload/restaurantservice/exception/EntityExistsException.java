package org.itmo.highload.restaurantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(reason = "Resource already exists", code = HttpStatus.CONFLICT)
public class EntityExistsException extends RuntimeException {
    public EntityExistsException(Class<?> Clazz, UUID firstId, UUID secondId) {
        super(String.format("Entity %s with key {%s, %s} already exists",
                Clazz.toString(),
                firstId.toString(),
                secondId.toString()));
    }
}