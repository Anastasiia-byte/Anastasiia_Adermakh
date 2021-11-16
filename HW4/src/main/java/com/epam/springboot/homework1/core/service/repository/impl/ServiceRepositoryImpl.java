package com.epam.springboot.homework1.core.service.repository.impl;

import com.epam.springboot.homework1.core.service.model.Abonent;
import com.epam.springboot.homework1.core.service.model.Service;
import com.epam.springboot.homework1.core.service.repository.AbonentRepository;
import com.epam.springboot.homework1.core.service.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ServiceRepositoryImpl implements ServiceRepository {
    private final List<Service> serviceList = new ArrayList<>();

    @Autowired
    private AbonentRepository abonentRepository;

    @Override
    public Service getService(int id) {
        log.info("get service with id {} from service repo", id);
        return serviceList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Service getService(String email, int id) {
        log.info("get abonent {}'s service with id {} from service repo", email, id);
        return abonentRepository.getServiceList(email)
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Service> listServices(String email) {
        log.info("get service list from service repo");
        return new ArrayList<>(abonentRepository.getServiceList(email));
    }

    @Override
    public List<Service> listServices() {
        return new ArrayList<>(serviceList);
    }

    @Override
    public Service createService(String email, Service service) {
        log.info("add new service to service list in service repo");
        abonentRepository.getServiceList(email).add(service);
        serviceList.add(service);
        return service;
    }

    @Override
    public Service updateService(int id, Service service) {
        log.info("update service with id {} in service repo", id);
        boolean isDeleted = serviceList.removeIf(s -> s.getId() == id);
        if(isDeleted){
            abonentRepository.getAbonentList().forEach(a -> a.getServiceList().removeIf(s -> s.getId() == id));
            serviceList.add(service);
            abonentRepository.getAbonentList().forEach(a -> a.getServiceList().add(service));
        } else {
            throw new RuntimeException("Service was not found");
        }
        return service;
    }

    @Override
    public void deleteService(int id) {
        log.info("delete service with id {} in service repo", id);
        serviceList.removeIf(s -> s.getId() == id);
        abonentRepository.getAbonentList().forEach(a -> a.getServiceList().removeIf(s -> s.getId() == id));
    }

    @Override
    public void deleteService(String email, int id) {
        log.info("delete service with id {} from abonent {}'s service list in service repo", email, id);
        abonentRepository.getServiceList(email).removeIf(s -> s.getId() == id);
    }
}
