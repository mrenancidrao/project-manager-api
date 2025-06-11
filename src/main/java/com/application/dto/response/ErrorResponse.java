package com.application.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String message,
        List<String> details,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(HttpStatus status, String message, List<String> details) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                details,
                LocalDateTime.now()
        );
    }
}

