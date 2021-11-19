package com.epam.springboot.homework1.core.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Service {
    private int id;
    private String name;

    private List<Abonent> abonentList;
    private List<Tariff> tariffList;

}
