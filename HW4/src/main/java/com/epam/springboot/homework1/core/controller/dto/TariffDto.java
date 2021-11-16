package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TariffDto {
    private int id;
    private String name;
    private int price;
}
