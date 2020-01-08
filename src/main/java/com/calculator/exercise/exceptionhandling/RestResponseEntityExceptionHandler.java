package com.calculator.exercise.exceptionhandling;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE_INVALID_INPUT = "Invalid input.";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
                                                            HttpServletResponse response) {

        List<String> details = ex.getConstraintViolations().stream()
                .map(e -> ((PathImpl) e.getPropertyPath()).getLeafNode().getName() + " - " + e.getMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new CustomErrorResponse(ERROR_MESSAGE_INVALID_INPUT, details));
    }

    @ExceptionHandler(ArithmeticException.class)
    protected ResponseEntity<CustomErrorResponse> handleConflict(
            RuntimeException ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomErrorResponse(ex.getMessage(), Collections.emptyList()));
    }
}
