package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.service.ServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(value = ServiceController.class)
@AutoConfigureMockMvc
public class ServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceService service;

    private final String MOCK_EMAIL = "email@email.com";

    @Test
    @DisplayName("Test getService")
    void getServiceTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.getService(1L)).thenReturn(serviceDto);

        mockMvc.perform(get("/service/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(serviceDto.getName()));
    }

    @Test
    @DisplayName("Test getAbonentService")
    void getAbonentServiceTest() throws Exception{

        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.getService(MOCK_EMAIL, 1L)).thenReturn(serviceDto);

        mockMvc.perform(get("/abonent/{email}/service/{id}", MOCK_EMAIL, 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(serviceDto.getName()));

    }

    @Test
    @DisplayName("Test getAllServices")
    void getAllServicesTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.listServices()).thenReturn(Collections.singletonList(serviceDto));

        mockMvc.perform(get("/service"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(serviceDto.getName()));
    }

    @Test
    @DisplayName("Test getService with Exception")
    void getServiceTest_WithException() throws Exception{
        when(service.getService(1L)).thenThrow(new EntityNotFoundException("service was not found"));

        mockMvc.perform(get("/service/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test createService")
    void createServiceTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.createService(serviceDto)).thenReturn(serviceDto);

        mockMvc.perform(post("/service")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(serviceDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(serviceDto.getName()));
    }

    @Test
    @DisplayName("Test createInvalidService")
    void createInvalidServiceTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                        .serviceId(1L)
                        .name("")
                        .build();

        mockMvc.perform(post("/service")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(serviceDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test updateService")
    void updateServiceTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        ServiceDto serviceUpdated = ServiceDto.builder()
                .serviceId(1L)
                .name("serviceee")
                .build();

        when(service.updateService(1L, serviceDto)).thenReturn(serviceUpdated);

        mockMvc.perform(MockMvcRequestBuilders.put("/service/{id}", 1L)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(serviceDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(serviceUpdated.getName()));

    }

    @Test
    @DisplayName("Test deleteService")
    void deleteServiceTest() throws Exception{
        doNothing().when(service).deleteService(1L);

        mockMvc.perform(delete("/service/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteService(1L);
    }

    @Test
    @DisplayName("Test subscribeAbonent")
    void subscribeAbonentTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.subscribeAbonentToService(MOCK_EMAIL, 1L)).thenReturn(serviceDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/abonent/{email}/service/{id}", MOCK_EMAIL, 1L))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test unsubscribeAbonent")
    void unsubscribeAbonentTest() throws Exception{
        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();

        when(service.unsubscribeAbonent(MOCK_EMAIL, 1L)).thenReturn(serviceDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/abonent/{email}/service/{id}", MOCK_EMAIL, 1L))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
