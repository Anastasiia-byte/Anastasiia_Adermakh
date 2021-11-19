package com.epam.springboot.homework1.core.repository;

import com.epam.springboot.homework1.core.model.Service;

import java.util.List;

public interface ServiceRepository {

    Service getService(int id);

    Service getService(String email, int id);

    List<Service> listServices(String email);

    List<Service> listServices();

    Service createService(Service service);

    Service updateService(int id, Service service);

    void deleteService(int id);

}
