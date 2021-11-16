package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    ServiceDto getService(int id);

    ServiceDto getService(String email, int id);

    List<ServiceDto> listServices(String email);

    List<ServiceDto> listServices();

    ServiceDto createService(String email, ServiceDto serviceDto);

    ServiceDto updateService(int id, ServiceDto serviceDto);

    void deleteService(int id);

    void deleteService(String email, int id);
}
