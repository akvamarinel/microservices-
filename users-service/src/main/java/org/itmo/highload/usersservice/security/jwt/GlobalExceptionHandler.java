package org.itmo.highload.usersservice.security.jwt;

import org.itmo.highload.usersservice.common.ResponseBody;
import org.itmo.highload.usersservice.exception.EntityExistsException;
import org.itmo.highload.usersservice.exception.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_NOT_FOUND = "Resource not found.";
    private static final String ERROR_EXISTS = "Resource already exists.";
    private static final String ERROR_VALIDATE_DATA = "Received incorrect data.";


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(WebRequest request, EntityNotFoundException exception) {

        var responseBody = ResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ERROR_NOT_FOUND)
                .error(exception.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> entityExistsException(WebRequest request, EntityNotFoundException exception) {

        var responseBody = ResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ERROR_EXISTS)
                .error(exception.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleConstraintViolationException(TransactionSystemException exception, WebRequest request) {

        var responseBody = ResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ERROR_VALIDATE_DATA)
                .error(exception.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               @NonNull HttpHeaders headers, HttpStatus status,
                                                               @NonNull WebRequest request) {

        var errors = Optional.of((exception).getBindingResult())
                .map(Errors::getAllErrors)
                .stream()
                .flatMap(Collection::stream)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        var responseBody = ResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(ERROR_VALIDATE_DATA)
                .error(errors.toString())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
    }
}
