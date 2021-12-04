package com.epam.springboot.homework1.core.mapper;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.model.Abonent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AbonentMapper {
    AbonentMapper INSTANCE = Mappers.getMapper(AbonentMapper.class);

    Abonent mapAbonentDto(AbonentDto abonentDto);

    AbonentDto mapAbonent(Abonent abonent);

    List<Abonent> mapAbonentDtoList(List<AbonentDto> abonentDtoList);

    List<AbonentDto> mapAbonentList(List<Abonent> abonentList);
}
