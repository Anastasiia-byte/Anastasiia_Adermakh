package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.mapper.TariffMapper;
import com.epam.springboot.homework1.core.service.TariffService;
import com.epam.springboot.homework1.core.model.Tariff;
import com.epam.springboot.homework1.core.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;

    @Override
    public TariffDto getTariff(String email, int serviceId, int id) {
        log.info("get tariff with id {} in tariff service", id);
        Tariff tariff = tariffRepository.getTariff(email, serviceId, id);
        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public List<TariffDto> listTariffs(String email, int serviceId) {
        log.info("list all tariffs in tariff service");
        List<Tariff> tariffList = tariffRepository.listTariffs(email, serviceId);
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public TariffDto createTariff(String email, int serviceId, TariffDto tariffDto) {
        log.info("create new tariff in tariff service");
        Tariff tariff = tariffRepository.createTariff(email, serviceId, TariffMapper.INSTANCE.mapTariffDto(tariffDto));
        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public TariffDto updateService(String email, int serviceId, int id, TariffDto tariffDto) {
        log.info("update tariff with id {} in tariff service", id);
        Tariff tariff = tariffRepository.updateService(email, serviceId, id, TariffMapper.INSTANCE.mapTariffDto(tariffDto));
        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public void deleteTariff(String email, int serviceId, int id) {
        log.info("delete tariff with id {}", id);
        tariffRepository.deleteTariff(email, serviceId, id);
    }

    @Override
    public List<TariffDto> sortByPrice(String email, int serviceId) {
        log.info("sort tariffs by price in tariff service");
        List<Tariff> tariffList = tariffRepository.sortByPrice(email, serviceId);
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public List<TariffDto> sortByNameStraight(String email, int serviceId) {
        log.info("sort tariffs by name alphabetically in tariff service");
        List<Tariff> tariffList = tariffRepository.sortByNameStraight(email, serviceId);
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public List<TariffDto> sortByNameOpposite(String email, int serviceId) {
        log.info("sort tariffs by name non-alphabetically in tariff service");
        List<Tariff> tariffList = tariffRepository.sortByNameOpposite(email, serviceId);
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

}
