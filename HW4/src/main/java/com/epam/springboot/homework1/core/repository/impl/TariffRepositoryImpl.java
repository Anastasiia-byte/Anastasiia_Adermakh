package com.epam.springboot.homework1.core.repository.impl;

import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.model.Tariff;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class TariffRepositoryImpl implements TariffRepository {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Tariff getTariff(String email, int serviceId, int id) {
        log.info("get tariff with id {} from tariff repo", id);
        return serviceRepository
                .getService(email, serviceId)
                .getTariffList()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Tariff " + id + " was not found"));
    }

    @Override
    public List<Tariff> listTariffs(String email, int serviceId) {
        log.info("list all tariffs from tariff repo");
        return new ArrayList<>(serviceRepository.getService(email, serviceId).getTariffList());
    }

    @Override
    public Tariff createTariff(String email, int serviceId, Tariff tariff) {
        log.info("add new tariff to tariff repo");
        serviceRepository.getService(email, serviceId).getTariffList().add(tariff);
        return tariff;
    }

    @Override
    public Tariff updateService(String email, int serviceId, int id, Tariff tariff) {
        log.info("update tariff with id {} in tariff repo", id);
        boolean isDeleted = serviceRepository.getService(email, serviceId).getTariffList().removeIf(t -> t.getId() == id);
        if(isDeleted){
            serviceRepository.getService(email, serviceId).getTariffList().add(tariff);
        } else {
            throw new EntityNotFoundException("Tariff was not found!");
        }
        return tariff;
    }

    @Override
    public void deleteTariff(String email, int serviceId, int id) {
        log.info("remove tariff with id {}", id);
        serviceRepository.getService(email, serviceId).getTariffList().removeIf(t -> t.getId() == id);
    }

    public List<Tariff> sortByPrice(String email, int serviceId){
        log.info("sort tariff list of service {} by price in tariff repo", serviceId);
        serviceRepository.getService(email, serviceId).getTariffList().sort(Comparator.comparingInt(Tariff::getPrice));
        return serviceRepository.getService(email, serviceId).getTariffList();
    }

    public List<Tariff> sortByNameStraight(String email, int serviceId){
        log.info("sort tariff list of service {} by name alphabetically in tariff repo", serviceId);
        serviceRepository.getService(email, serviceId).getTariffList().sort(Comparator.comparing(Tariff::getName));
        return serviceRepository.getService(email, serviceId).getTariffList();
    }

    public List<Tariff> sortByNameOpposite(String email, int serviceId){
        log.info("sort tariff list of service {} by name non-alphabetically in tariff repo", serviceId);
        serviceRepository.getService(email, serviceId).getTariffList().sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
        return serviceRepository.getService(email, serviceId).getTariffList();
    }
}
