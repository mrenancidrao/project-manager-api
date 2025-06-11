package com.application.service;

import com.application.dto.request.EmployeeRequest;
import com.application.dto.response.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    List<EmployeeResponse> findAll();
    EmployeeResponse findById(UUID id);
}
