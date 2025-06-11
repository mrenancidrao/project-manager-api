package com.application.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ProjectRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        LocalDateTime creationDate,

        String description,

        LocalDateTime startDate,

        LocalDateTime endDate,

        List<UUID> employeesIds) {
}
