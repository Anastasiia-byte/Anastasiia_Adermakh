package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;

import java.util.List;

public interface TariffService {
    TariffDto getTariff(Long id);

    List<TariffDto> listTariffs(Long serviceId);

    TariffDto createTariff(Long serviceId, TariffDto tariff);

    TariffDto updateTariff(Long serviceId, Long tariffId, TariffDto tariff);

    void deleteTariff(Long id);

    List<TariffDto> sortByPrice(Long serviceId);

    List<TariffDto> sortByNameStraight(Long serviceId);

    List<TariffDto> sortByNameOpposite(Long serviceId);
}
