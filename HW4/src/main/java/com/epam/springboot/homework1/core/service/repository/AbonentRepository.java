package com.epam.springboot.homework1.core.service.repository;

import com.epam.springboot.homework1.core.service.model.Abonent;
import com.epam.springboot.homework1.core.service.model.Service;

import java.util.List;

public interface AbonentRepository {

    Abonent getAbonent(String email);

    List<Abonent> listAbonents();

    Abonent createAbonent(Abonent abonent);

    Abonent updateAbonent(String email, Abonent abonent);

    void deleteAbonent(String email);

    List<Service> getServiceList(String email);

    List<Abonent> getAbonentList();
}
