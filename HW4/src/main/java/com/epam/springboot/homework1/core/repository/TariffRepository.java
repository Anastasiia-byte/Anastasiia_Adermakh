package com.epam.springboot.homework1.core.repository;

import com.epam.springboot.homework1.core.model.Tariff;

import java.util.List;

public interface TariffRepository {

    Tariff getTariff(String email, int serviceId, int id);

    List<Tariff> listTariffs(String email, int serviceId);

    Tariff createTariff(String email, int serviceId, Tariff tariff);

    Tariff updateService(String email, int serviceId, int id, Tariff tariff);

    void deleteTariff(String email, int serviceId, int id);

    List<Tariff> sortByPrice(String email, int serviceId);

    List<Tariff> sortByNameStraight(String email, int serviceId);

    List<Tariff> sortByNameOpposite(String email, int serviceId);
}
