package com.epam.springboot.homework1.core.service.impl;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.service.AbonentService;
import com.epam.springboot.homework1.core.service.model.Abonent;
import com.epam.springboot.homework1.core.service.repository.AbonentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbonentServiceImpl implements AbonentService {
    private final AbonentRepository abonentRepository;

    @Override
    public AbonentDto getAbonent(String email) {
        log.info("get abonent by {}", email);
        Abonent abonent = abonentRepository.getAbonent(email);
        return mapAbonentToAbonentDto(abonent);
    }

    @Override
    public List<AbonentDto> listAbonents() {
        log.info("get all abonents");
        return abonentRepository.listAbonents()
                .stream()
                .map(this::mapAbonentToAbonentDto)
                .collect(Collectors.toList());
    }

    @Override
    public AbonentDto createAbonent(AbonentDto abonentDto) {
        log.info("create abonent with {}", abonentDto.getEmail());
        Abonent abonent = mapAbonentDtoToAbonent(abonentDto);
        abonentRepository.createAbonent(abonent);
        return mapAbonentToAbonentDto(abonent);
    }

    @Override
    public AbonentDto updateAbonent(String email, AbonentDto abonentDto) {
        log.info("update abonent with {}", email);
        Abonent abonent = mapAbonentDtoToAbonent(abonentDto);
        abonentRepository.updateAbonent(email, abonent);
        return mapAbonentToAbonentDto(abonent);
    }

    @Override
    public void deleteAbonent(String email) {
        log.info("delete abonent with {}", email);
        abonentRepository.deleteAbonent(email);
    }

    //TODO: replace with BeanUtils or MapStruct
    //for now
    private AbonentDto mapAbonentToAbonentDto(Abonent abonent){
        return AbonentDto.builder()
                .email(abonent.getEmail())
                .firstName(abonent.getFirstName())
                .lastName(abonent.getLastName())
                .build();
    }

    private Abonent mapAbonentDtoToAbonent(AbonentDto abonentDto){
        return Abonent.builder()
                .email(abonentDto.getEmail())
                .firstName(abonentDto.getFirstName())
                .lastName(abonentDto.getLastName())
                .serviceList(new ArrayList<>())
                .build();
    }
}
