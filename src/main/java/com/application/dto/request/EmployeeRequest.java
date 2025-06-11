package com.application.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EmployeeRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        @NotBlank(message = "Cpf é obrigatório.")
        String cpf,

        @Email(message = "Email inválido.")
        @NotBlank(message = "Email é obrigatório.")
        String email,

        @NotNull(message = "Salário é obrigatório.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero")
        BigDecimal salary
) {}
