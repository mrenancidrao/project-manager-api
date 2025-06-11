package com.application.service.impl;

import com.application.dto.request.EmployeeRequest;
import com.application.dto.response.EmployeeResponse;
import com.application.entity.Employee;
import com.application.exception.ResourceNotFoundException;
import com.application.repository.EmployeeRepository;
import com.application.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = Employee.builder()
                        .name(request.name())
                        .cpf(request.cpf())
                        .email(request.email())
                        .salary(request.salary())
                        .build();

        return toResponse(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse findById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        return toResponse(employee);
    }

    private EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .cpf(employee.getCpf())
                .email(employee.getEmail())
                .build();
    }
}
