package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.mapper.ServiceMapper;
import com.epam.springboot.homework1.core.model.Abonent;
import com.epam.springboot.homework1.core.repository.AbonentRepository;
import com.epam.springboot.homework1.core.service.ServiceService;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final AbonentRepository abonentRepository;

    @Override
    public ServiceDto subscribeAbonentToService(String email, Long id){
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(id)
                .orElseThrow(() -> new EntityNotFoundException("Service " + id + " was not found"));
        Abonent abonent = abonentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Abonent " + email + " was not found"));
        service.enroll(abonent);
        serviceRepository.save(service);
        abonent.subscribe(service);
        abonentRepository.save(abonent);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto unsubscribeAbonent(String email, Long id){
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(id)
                .orElseThrow(() -> new EntityNotFoundException("Service " + id + " was not found"));
        Abonent abonent = abonentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Abonent " + email + " was not found"));
        abonent.unsubscribe(service);
        serviceRepository.save(service);
        service.extract(abonent);
        abonentRepository.save(abonent);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto getService(Long id) {
        log.info("get service with id {}", id);
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(id)
                .orElseThrow(() -> new EntityNotFoundException("Service " + id + " was not found"));
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto getService(String email, Long id) {
        log.info("get abonent {}'s service with id {}", email, id);
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceIdAndAbonentList_Email(id, email);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public List<ServiceDto> listServices(String email) {
        log.info("get abonent's {} list of services", email);
        return ServiceMapper.INSTANCE.mapServiceList(serviceRepository.findAllByAbonentList_Email(email));
    }

    @Override
    public List<ServiceDto> listServices() {
        log.info("get list of services");
        return ServiceMapper.INSTANCE.mapServiceList(serviceRepository.findAll());
    }

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        log.info("create new service");
        com.epam.springboot.homework1.core.model.Service service = ServiceMapper.INSTANCE.mapServiceDto(serviceDto);
        serviceRepository.save(service);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public ServiceDto updateService(Long id, ServiceDto serviceDto) {
        log.info("update service with id {}", id);
        com.epam.springboot.homework1.core.model.Service service = ServiceMapper.INSTANCE.mapServiceDto(serviceDto);
        serviceRepository.updateById(service.getName(), id);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public void deleteService(Long id) {
        log.info("delete service with id {}", id);
        serviceRepository.deleteByServiceId(id);
    }

}
