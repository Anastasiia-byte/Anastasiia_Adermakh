package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
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

    @Override
    public AbonentDto getAbonent(String email) {
        log.info("get abonent by {}", email);
        Abonent abonent = abonentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Abonent " + email + " was not found"));
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public List<AbonentDto> listAbonents() {
        log.info("get all abonents");
        return AbonentMapper.INSTANCE.mapAbonentList(abonentRepository.findAll());
    }

    @Override
    public AbonentDto createAbonent(AbonentDto abonentDto) {
        log.info("create abonent with {}", abonentDto.getEmail());
        Abonent abonent = AbonentMapper.INSTANCE.mapAbonentDto(abonentDto);
        abonentRepository.save(abonent);
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public AbonentDto updateAbonent(String email, AbonentDto abonentDto) {
        log.info("update abonent with {}", email);
        Abonent abonent = AbonentMapper.INSTANCE.mapAbonentDto(abonentDto);
        abonentRepository.updateById(abonent.firstName, abonent.lastName, abonent.abonentId);
        return AbonentMapper.INSTANCE.mapAbonent(abonent);
    }

    @Override
    public void deleteAbonent(String email) {
        log.info("delete abonent with {}", email);
        abonentRepository.deleteByEmail(email);
    }

}
