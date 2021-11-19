package com.epam.springboot.homework1.core.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Abonent {
    private String firstName;
    private String lastName;
    private String email;
    public List<Service> serviceList;
}
