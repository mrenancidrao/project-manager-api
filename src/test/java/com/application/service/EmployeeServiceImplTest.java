package com.application.service;

import com.application.dto.request.EmployeeRequest;
import com.application.dto.response.EmployeeResponse;
import com.application.entity.Employee;
import com.application.exception.ResourceNotFoundException;
import com.application.repository.EmployeeRepository;
import com.application.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest("Renan", "12345678900", "renan.cidrao@teste.com", BigDecimal.valueOf(5000.00));
        Employee saved = Employee.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .cpf(request.cpf())
                .email(request.email())
                .salary(request.salary())
                .build();

        when(employeeRepository.save(any())).thenReturn(saved);

        EmployeeResponse result = employeeService.create(request);

        assertThat(result.name()).isEqualTo("Renan");
        verify(employeeRepository).save(any());
    }

    @Test
    void shouldFindAllEmployees() {
        List<Employee> employees = List.of(Employee.builder().id(UUID.randomUUID()).name("Cidrão").build());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeResponse> result = employeeService.findAll();

        assertThat(result).hasSize(1);
        verify(employeeRepository).findAll();
    }

    @Test
    void shouldFindEmployeeById() {
        UUID id = UUID.randomUUID();
        Employee employee = Employee.builder().id(id).name("Renan Cidrão").build();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeResponse result = employeeService.findById(id);

        assertThat(result.id()).isEqualTo(id);
        verify(employeeRepository).findById(id);
    }

    @Test
    void shouldThrowWhenEmployeeNotFound() {
        UUID id = UUID.randomUUID();
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Employee not found with ID");

        verify(employeeRepository).findById(id);
    }
}
