package com.epam.springboot.homework1.core.service.repository.impl;

import com.epam.springboot.homework1.core.service.model.Abonent;
import com.epam.springboot.homework1.core.service.model.Service;
import com.epam.springboot.homework1.core.service.repository.AbonentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AbonentRepositoryImpl implements AbonentRepository {

    private final List<Abonent> list = new ArrayList<>();

    @Override
    public Abonent getAbonent(String email) {
        log.info("get abonent with email {} from abonent repo", email);
        return list
                .stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Abonent> listAbonents() {
        log.info("get abonents list from abonent repo");
        return new ArrayList<>(list);
    }

    @Override
    public Abonent createAbonent(Abonent abonent) {
        log.info("add new abonent to abonents list in abonent repo");
        list.add(abonent);
        return abonent;
    }

    @Override
    public Abonent updateAbonent(String email, Abonent abonent) {
        log.info("update abonent with email {} in abonent repo", email);
        boolean isDeleted = list.removeIf(u -> u.getEmail().equals(email));
        if(isDeleted){
            list.add(abonent);
        } else {
            throw new RuntimeException("Abonent was not found!");
        }
        return abonent;
    }

    @Override
    public void deleteAbonent(String email) {
        log.info("delete abonent with email {} in abonent repo", email);
        list.removeIf(u -> u.getEmail().equals(email));
    }

    @Override
    public List<Service> getServiceList(String email) {
        log.info("get abonent's with email {} list of services in abonent repo", email);
        return list
                .stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getServiceList();
    }

    public List<Abonent> getAbonentList() {
        return list;
    }
}
