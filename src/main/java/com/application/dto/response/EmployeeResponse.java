package com.application.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EmployeeResponse(UUID id, String name, String cpf, String email) {
}
