package com.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ProjectResponse(
        UUID id,
        String name,
        LocalDateTime creationDate,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<EmployeeResponse> employees
) {
}

