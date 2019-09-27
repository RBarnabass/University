package com.junior.university.exceptions;

import lombok.extern.log4j.Log4j2;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UniversityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniversityException.class)
    protected ResponseEntity<Object> handleCustomRuntimeException(final UniversityException exception) {

        final ApiError apiError = new ApiError();
        final ResponseStatus responseStatus = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);

        if (responseStatus != null) {
            apiError.setStatus(responseStatus.code());
        }

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(final ApiError apiError) {
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError.getStatus());
    }
}
