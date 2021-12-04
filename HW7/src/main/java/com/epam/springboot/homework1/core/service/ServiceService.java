package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    ServiceDto getService(Long id);

    ServiceDto getService(String email, Long id);

    List<ServiceDto> listServices(String email);

    List<ServiceDto> listServices();

    ServiceDto createService(ServiceDto serviceDto);

    ServiceDto subscribeAbonentToService(String email, Long id);

    ServiceDto unsubscribeAbonent(String email, Long id);

    ServiceDto updateService(Long id, ServiceDto serviceDto);

    void deleteService(Long id);

}
