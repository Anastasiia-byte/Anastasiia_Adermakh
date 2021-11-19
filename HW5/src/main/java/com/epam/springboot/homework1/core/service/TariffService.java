package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;

import java.util.List;

public interface TariffService {
    TariffDto getTariff(String email, int serviceId, int id);

    List<TariffDto> listTariffs(String email, int serviceId);

    TariffDto createTariff(String email, int serviceId, TariffDto tariff);

    TariffDto updateService(String email, int serviceId, int id, TariffDto tariff);

    void deleteTariff(String email, int serviceId, int id);

    List<TariffDto> sortByPrice(String email, int serviceId);

    List<TariffDto> sortByNameStraight(String email, int serviceId);

    List<TariffDto> sortByNameOpposite(String email, int serviceId);
}
