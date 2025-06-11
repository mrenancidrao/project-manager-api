package com.application.service.impl;

import com.application.dto.request.ProjectRequest;
import com.application.dto.response.EmployeeResponse;
import com.application.dto.response.ProjectResponse;
import com.application.entity.Employee;
import com.application.entity.Project;
import com.application.exception.ResourceNotFoundException;
import com.application.repository.EmployeeRepository;
import com.application.repository.ProjectRepository;
import com.application.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        List<UUID> ids = Optional.ofNullable(request.employeesIds()).orElse(List.of());
        List<Employee> employees = employeeRepository.findAllById(ids);

        Project project = Project.builder()
                .name(request.name())
                .creationDate(LocalDateTime.now(ZoneId.of("UTC")))
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .employees(employees)
                .build();

        return toResponse(projectRepository.save(project));
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse findById(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        return toResponse(project);
    }

    private ProjectResponse toResponse(Project project) {
        List<EmployeeResponse> employeeResponses = project.getEmployees().stream()
                .map(emp -> EmployeeResponse.builder()
                        .id(emp.getId())
                        .name(emp.getName())
                        .cpf(emp.getCpf())
                        .email(emp.getEmail())
                        .build())
                .toList();

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .creationDate(project.getCreationDate())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .employees(employeeResponses)
                .build();
    }
}
