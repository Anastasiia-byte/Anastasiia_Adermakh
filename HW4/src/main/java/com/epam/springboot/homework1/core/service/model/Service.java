package com.epam.springboot.homework1.core.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Service {
    private int id;
    private String name;

    private List<Tariff> tariffList;
}
