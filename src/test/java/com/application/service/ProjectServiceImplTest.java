package com.application.service;

import com.application.dto.request.ProjectRequest;
import com.application.dto.response.ProjectResponse;
import com.application.entity.Project;
import com.application.exception.ResourceNotFoundException;
import com.application.repository.EmployeeRepository;
import com.application.repository.ProjectRepository;
import com.application.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProject() {
        ProjectRequest request = new ProjectRequest("Projeto de Integração", "Desc", LocalDateTime.now(), LocalDateTime.now(), List.of());
        Project saved = Project.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .creationDate(LocalDateTime.now(ZoneId.of("UTC")))
                .description(request.description())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .employees(new ArrayList<>())
                .build();

        when(employeeRepository.findAllById(any())).thenReturn(new ArrayList<>());
        when(projectRepository.save(any())).thenReturn(saved);

        ProjectResponse result = projectService.createProject(request);

        assertThat(result.name()).isEqualTo("Projeto de Integração");
        verify(projectRepository).save(any());
    }

    @Test
    void shouldFindAllProjects() {
        List<Project> projects = List.of(Project.builder().id(UUID.randomUUID()).name("P1").employees(new ArrayList<>()).build());
        when(projectRepository.findAll()).thenReturn(projects);

        List<ProjectResponse> result = projectService.findAll();

        assertThat(result).hasSize(1);
        verify(projectRepository).findAll();
    }

    @Test
    void shouldFindProjectById() {
        UUID id = UUID.randomUUID();
        Project project = Project.builder().id(id).name("Project Teste").employees(new ArrayList<>()).build();
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        ProjectResponse result = projectService.findById(id);

        assertThat(result.id()).isEqualTo(id);
        verify(projectRepository).findById(id);
    }

    @Test
    void shouldThrowWhenProjectNotFound() {
        UUID id = UUID.randomUUID();
        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Project not found with ID");

        verify(projectRepository).findById(id);
    }
}
