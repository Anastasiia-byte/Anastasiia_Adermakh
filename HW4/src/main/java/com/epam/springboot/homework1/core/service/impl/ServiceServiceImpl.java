package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.service.ServiceService;
import com.epam.springboot.homework1.core.service.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public ServiceDto getService(int id) {
        log.info("get service with id {}", id);
        com.epam.springboot.homework1.core.service.model.Service service = serviceRepository.getService(id);
        return mapServiceToServiceDto(service);
    }

    @Override
    public ServiceDto getService(String email, int id) {
        log.info("get abonent {}'s service with id {}", email, id);
        com.epam.springboot.homework1.core.service.model.Service service = serviceRepository.getService(email, id);
        return mapServiceToServiceDto(service);
    }

    @Override
    public List<ServiceDto> listServices(String email) {
        log.info("get abonent's {} list of services", email);
        return serviceRepository.listServices(email)
                .stream()
                .map(this::mapServiceToServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> listServices() {
        log.info("get list of services");
        return serviceRepository.listServices()
                .stream()
                .map(this::mapServiceToServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDto createService(String email, ServiceDto serviceDto) {
        log.info("create new service");
        com.epam.springboot.homework1.core.service.model.Service service = mapServiceDtoToService(serviceDto);
        serviceRepository.createService(email, service);
        return mapServiceToServiceDto(service);
    }

    @Override
    public ServiceDto updateService(int id, ServiceDto serviceDto) {
        log.info("update service with id {}", id);
        com.epam.springboot.homework1.core.service.model.Service service = mapServiceDtoToService(serviceDto);
        serviceRepository.updateService(id, service);
        return mapServiceToServiceDto(service);
    }

    @Override
    public void deleteService(int id) {
        log.info("delete service with id {}", id);
        serviceRepository.deleteService(id);
    }

    @Override
    public void deleteService(String email, int id) {
        log.info("delete service with id {} from abonent {}'s service list", id, email);
        serviceRepository.deleteService(email, id);
    }

    //TODO: replace with BeanUtils or MapStruct
    //for now
    private ServiceDto mapServiceToServiceDto(com.epam.springboot.homework1.core.service.model.Service service){
        return ServiceDto.builder()
                .id(service.getId())
                .name(service.getName())
                .build();
    }

    private com.epam.springboot.homework1.core.service.model.Service mapServiceDtoToService(ServiceDto serviceDto){
        return com.epam.springboot.homework1.core.service.model.Service.builder()
                .id(serviceDto.getId())
                .name(serviceDto.getName())
                .tariffList(new ArrayList<>())
                .build();
    }
}
