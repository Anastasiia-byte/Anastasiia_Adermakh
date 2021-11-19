package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.mapper.ServiceMapper;
import com.epam.springboot.homework1.core.service.ServiceService;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public ServiceDto getService(int id) {
        log.info("get service with id {}", id);
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.getService(id);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto getService(String email, int id) {
        log.info("get abonent {}'s service with id {}", email, id);
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.getService(email, id);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public List<ServiceDto> listServices(String email) {
        log.info("get abonent's {} list of services", email);
        return ServiceMapper.INSTANCE.mapServiceList(serviceRepository.listServices(email));
    }

    @Override
    public List<ServiceDto> listServices() {
        log.info("get list of services");
        return ServiceMapper.INSTANCE.mapServiceList(serviceRepository.listServices());
    }

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        log.info("create new service");
        com.epam.springboot.homework1.core.model.Service service = ServiceMapper.INSTANCE.mapServiceDto(serviceDto);
        service.setAbonentList(new ArrayList<>());
        service.setTariffList(new ArrayList<>());
        serviceRepository.createService(service);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto updateService(int id, ServiceDto serviceDto) {
        log.info("update service with id {}", id);
        com.epam.springboot.homework1.core.model.Service service = ServiceMapper.INSTANCE.mapServiceDto(serviceDto);
        serviceRepository.updateService(id, service);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public void deleteService(int id) {
        log.info("delete service with id {}", id);
        serviceRepository.deleteService(id);
    }

}
