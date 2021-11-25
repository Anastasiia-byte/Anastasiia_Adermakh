package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AbonentService {
    AbonentDto getAbonent(String email);

    List<AbonentDto> listAbonents();

    AbonentDto createAbonent(AbonentDto AbonentDto);

    AbonentDto updateAbonent(String email, AbonentDto userDto);

    void deleteAbonent(String email);


}
