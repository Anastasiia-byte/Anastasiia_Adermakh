package com.epam.springboot.homework1.core.mapper;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.model.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TariffMapper {
    TariffMapper INSTANCE = Mappers.getMapper(TariffMapper.class);

    Tariff mapTariffDto(TariffDto tariffDto);

    TariffDto mapTariff(Tariff tariff);

    List<Tariff> mapTariffDtoList(List<TariffDto> tariffDtoList);

    List<TariffDto> mapTariffList(List<Tariff> tariffList);
}
