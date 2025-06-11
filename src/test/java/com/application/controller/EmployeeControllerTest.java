package com.application.controller;

import com.application.dto.request.EmployeeRequest;
import com.application.dto.response.EmployeeResponse;
import com.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    private ObjectMapper objectMapper;
    private UUID id;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        id = UUID.randomUUID();
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeRequest request = new EmployeeRequest("Renan", "12345678900", "renan@teste.com", BigDecimal.valueOf(5000.00));
        EmployeeResponse response = EmployeeResponse.builder().id(id).name("Renan").cpf("12345678900").email("renan@teste.com").build();

        Mockito.when(employeeService.create(any())).thenReturn(response);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Renan"));
    }

    @Test
    void shouldFindAllEmployees() throws Exception {
        List<EmployeeResponse> responses = List.of(EmployeeResponse.builder().id(id).name("Renan").cpf("123456789").email("renan@teste.com").build());

        Mockito.when(employeeService.findAll()).thenReturn(responses);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Renan"));
    }

    @Test
    void shouldFindEmployeeById() throws Exception {
        EmployeeResponse response = EmployeeResponse.builder().id(id).name("Maria").cpf("888").email("maria@email.com").build();

        Mockito.when(employeeService.findById(id)).thenReturn(response);

        mockMvc.perform(get("/employees/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maria"));
    }
}
