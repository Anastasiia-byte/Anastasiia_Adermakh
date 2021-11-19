package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class TariffDto {
    @NotBlank
    private int id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Min(100)
    private int price;
}
