package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceDto {
    private int id;
    private String name;

    private List<TariffDto> tariffDtoList;
}
