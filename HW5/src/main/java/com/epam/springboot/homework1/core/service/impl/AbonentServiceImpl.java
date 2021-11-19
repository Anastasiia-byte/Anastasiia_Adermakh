package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.mapper.AbonentMapper;
import com.epam.springboot.homework1.core.mapper.ServiceMapper;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.service.AbonentService;
import com.epam.springboot.homework1.core.model.Abonent;
import com.epam.springboot.homework1.core.repository.AbonentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbonentServiceImpl implements AbonentService {
    private final AbonentRepository abonentRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public AbonentDto getAbonent(String email) {
        log.info("get abonent by {}", email);
        Abonent abonent = abonentRepository.getAbonent(email);
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public List<AbonentDto> listAbonents() {
        log.info("get all abonents");
        return AbonentMapper.INSTANCE.mapAbonentList(abonentRepository.getAbonentList());
    }

    @Override
    public AbonentDto createAbonent(AbonentDto abonentDto) {
        log.info("create abonent with {}", abonentDto.getEmail());
        Abonent abonent = AbonentMapper.INSTANCE.mapAbonentDto(abonentDto);
        abonentRepository.createAbonent(abonent);
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public AbonentDto updateAbonent(String email, AbonentDto abonentDto) {
        log.info("update abonent with {}", email);
        Abonent abonent = AbonentMapper.INSTANCE.mapAbonentDto(abonentDto);
        abonentRepository.updateAbonent(email, abonent);
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public void deleteAbonent(String email) {
        log.info("delete abonent with {}", email);
        abonentRepository.deleteAbonent(email);
    }

    @Override
    public ServiceDto subscribeAbonent
            (String email, int id) {
        com.epam.springboot.homework1.core.model.Service service = serviceRepository.getService(id);
        List<Abonent> abonentList = service.getAbonentList();
        Abonent abonent = abonentRepository.getAbonent(email);
        abonentList.add(abonent);
        service.setAbonentList(abonentList);

        List<com.epam.springboot.homework1.core.model.Service> serviceList = abonent.getServiceList();
        if(serviceList == null){
            serviceList = new ArrayList<>();
        }
        serviceList.add(service);
        abonent.setServiceList(serviceList);
        return ServiceMapper.INSTANCE.mapService(service);
    }

    @Override
    public AbonentDto unsubscribeAbonent(String email, int id) {
        log.info("unsubscribe (abonent controller)");
        return AbonentMapper.INSTANCE.mapAbonent(abonentRepository.deleteService(email, id));

    }

}
