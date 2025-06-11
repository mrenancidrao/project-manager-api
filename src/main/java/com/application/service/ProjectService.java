package com.application.service;

import com.application.dto.request.ProjectRequest;
import com.application.dto.response.ProjectResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest dto);
    List<ProjectResponse> findAll();
    ProjectResponse findById(UUID id);
}
