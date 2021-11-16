package com.epam.springboot.homework1.core.service.repository;

import com.epam.springboot.homework1.core.service.model.Service;

import java.util.List;

public interface ServiceRepository {

    Service getService(int id);

    Service getService(String email, int id);

    List<Service> listServices(String email);

    List<Service> listServices();

    Service createService(String email, Service service);

    Service updateService(int id, Service service);

    void deleteService(int id);

    void deleteService(String email, int id);
}
