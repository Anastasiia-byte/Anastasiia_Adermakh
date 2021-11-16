package com.epam.springboot.homework1.core.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Abonent {
    private String firstName;
    private String lastName;
    private String email;

    private List<Service> serviceList;
}
