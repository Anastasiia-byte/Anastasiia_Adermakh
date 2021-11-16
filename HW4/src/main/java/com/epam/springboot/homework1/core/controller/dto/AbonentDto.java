package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AbonentDto {
    private String firstName;
    private String lastName;
    private String email;

    private List<ServiceDto> serviceDtoList;
}
