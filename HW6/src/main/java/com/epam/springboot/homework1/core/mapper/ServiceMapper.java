package com.epam.springboot.homework1.core.mapper;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    Service mapServiceDto(ServiceDto serviceDto);

    ServiceDto mapService(Service service);

    List<Service> mapServiceDtoList(List<ServiceDto> serviceDtoList);

    List<ServiceDto> mapServiceList(List<Service> serviceList);
}
