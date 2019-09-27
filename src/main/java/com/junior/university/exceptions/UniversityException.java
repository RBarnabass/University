package com.junior.university.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UniversityException extends RuntimeException {

    private String message;

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }
}
