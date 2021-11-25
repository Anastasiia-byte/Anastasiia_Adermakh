package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.*;

@Data
@Builder
public class TariffDto {
    @Positive
    private Long tariffId;
    @Size(min = 3)
    private String name;
    @Min(100)
    private Long price;
}
