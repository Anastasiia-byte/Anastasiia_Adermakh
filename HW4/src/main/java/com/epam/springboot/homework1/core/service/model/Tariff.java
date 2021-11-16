package com.epam.springboot.homework1.core.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tariff {
    private int id;
    private String name;
    private int price;

}
