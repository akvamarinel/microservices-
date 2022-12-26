package org.itmo.highload.restaurantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(reason = "Resource not found", code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> clazz, UUID id) {
        super(String.format("No such entity: %s %s", clazz.getSimpleName(), id.toString()));
    }
}
