package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.mapper.TariffMapper;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.service.TariffService;
import com.epam.springboot.homework1.core.model.Tariff;
import com.epam.springboot.homework1.core.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public TariffDto getTariff(Long id) {
        log.info("get tariff with id {} in tariff service", id);
        Tariff tariff = tariffRepository.findByService_TariffList_TariffId(id)
                .orElseThrow(() -> new EntityNotFoundException("Tariff " + id + " was not found"));
        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public List<TariffDto> listTariffs(Long serviceId) {
        log.info("list all tariffs in tariff service");
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        List<Tariff> tariffList = tariffRepository.findAllByService(service);
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public TariffDto createTariff(Long serviceId, TariffDto tariffDto) {
        log.info("create new tariff in tariff service");
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        Tariff tariff = TariffMapper.INSTANCE.mapTariffDto(tariffDto);
        tariff.setService(service);
        service.add(tariff);
        tariffRepository.save(tariff);

        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public TariffDto updateTariff(Long serviceId, Long tariffId, TariffDto tariffDto) {
        log.info("update tariff with id {} in tariff service", tariffId);
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        Tariff tariff = TariffMapper.INSTANCE.mapTariffDto(tariffDto);
        service.remove(tariff);
//        Set<Tariff> tariffSet = service.getTariffList();
//        tariffSet.remove(tariff);
        tariff = tariffRepository.updateById(tariff.getName(), tariff.getPrice(),tariffId);
//        tariffSet.add(tariff);
//        service.setTariffList(tariffSet);
        service.add(tariff);
        return TariffMapper.INSTANCE.mapTariff(tariff);
    }

    @Override
    public void deleteTariff(Long id) {
        log.info("delete tariff with id {}", id);
        Tariff tariff = tariffRepository.findByService_TariffList_TariffId(id)
                .orElseThrow(() -> new EntityNotFoundException("Tariff " + id + " was not found"));
        com.epam.springboot.homework1.core.model.Service service = tariff.getService();
        service.remove(tariff);
        tariffRepository.deleteByTariffId(id);
    }

    @Override
    public List<TariffDto> sortByPrice(Long serviceId) {
        log.info("sort tariffs by price in tariff service");
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        List<Tariff> tariffList = tariffRepository.findAllByService(service, Sort.by(Sort.Direction.ASC, "price"));
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public List<TariffDto> sortByNameStraight(Long serviceId) {
        log.info("sort tariffs by name alphabetically in tariff service");
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        List<Tariff> tariffList = tariffRepository.findAllByService(service, Sort.by(Sort.Direction.ASC, "name"));
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

    @Override
    public List<TariffDto> sortByNameOpposite(Long serviceId) {
        log.info("sort tariffs by name non-alphabetically in tariff service");
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.findByServiceId(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service " + serviceId + " was not found"));
        List<Tariff> tariffList = tariffRepository.findAllByService(service, Sort.by(Sort.Direction.DESC, "name"));
        return TariffMapper.INSTANCE.mapTariffList(tariffList);
    }

}
