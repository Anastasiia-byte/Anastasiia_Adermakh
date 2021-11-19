package com.epam.springboot.homework1.core.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class ServiceDto {
    @NotBlank
    private int id;
    @NotBlank
    @Size(min = 3)
    private String name;
}
