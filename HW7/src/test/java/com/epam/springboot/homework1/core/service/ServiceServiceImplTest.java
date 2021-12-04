package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.model.Abonent;
import com.epam.springboot.homework1.core.model.Service;
import com.epam.springboot.homework1.core.repository.AbonentRepository;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.service.impl.ServiceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceImplTest {
    @InjectMocks
    private ServiceServiceImpl service;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private AbonentRepository abonentRepository;

    @Test
    @DisplayName("Test findById Service")
    void getServiceTest(){
        Service expectedService = new Service();
        expectedService.setServiceId(1L);
        expectedService.setName("service");

        when(serviceRepository.findByServiceId(1L)).thenReturn(java.util.Optional.of(expectedService));

        ServiceDto actualService = service.getService(1L);

        assertEquals(expectedService.getName(), actualService.getName());
    }

    @Test
    @DisplayName("Test findAll Services")
    void getAllServicesTest(){
        when(serviceRepository.findAll()).thenReturn(Collections.singletonList(new Service()));

        List<ServiceDto> serviceDtoList = service.listServices();

        assertThat(serviceDtoList, hasSize(1));
    }

    @Test
    @DisplayName("Test deleteById Service")
    void deleteServiceTest(){
        doNothing().when(serviceRepository).deleteByServiceId(1L);

        service.deleteService(1L);

        verify(serviceRepository, times(1)).deleteByServiceId(1L);
    }

    @Test
    @DisplayName("Test deleteById Service with Exception")
    void deleteServiceWithExceptionTest(){
        doThrow(RuntimeException.class).when(serviceRepository).deleteByServiceId(any(Long.class));

        assertThrows(RuntimeException.class,
                () -> service.deleteService(1L));
    }

    @Test
    @DisplayName("Test update Service")
    void updateServiceTest(){
        Service serviceModel = new Service();
        serviceModel.setServiceId(1L);
        serviceModel.setName("service");

        ServiceDto serviceDto = ServiceDto.builder()
                .serviceId(1L)
                .name("service")
                .build();
        doNothing().when(serviceRepository).updateById(serviceModel.getName(), serviceModel.getServiceId());

        service.updateService(1L, serviceDto);

        verify(serviceRepository, times(1)).updateById(serviceModel.getName(), serviceModel.getServiceId());
    }

    @Test
    @DisplayName("Test subscribe Service")
    void subscribeAbonentTest(){
        String email = "email@email.com";
        Abonent abonent = new Abonent();
        abonent.setEmail(email);

        Service s = new Service();
        s.setServiceId(1L);

        when(abonentRepository.findByEmail(any(String.class))).thenReturn(java.util.Optional.of(abonent));
        when(serviceRepository.findByServiceId(1L)).thenReturn(java.util.Optional.of(s));

        when(serviceRepository.save(s)).thenReturn(s);
        when(abonentRepository.save(abonent)).thenReturn(abonent);

        service.subscribeAbonentToService(email, 1L);

        assertThat(s.getAbonentList(), hasSize(1));
    }

    @Test
    @DisplayName("Test unsubscribe Service")
    void unsubscribeAbonentTest(){
        String email = "email@email.com";
        Abonent abonent = new Abonent();
        abonent.setEmail(email);

        Service s = new Service();
        s.setServiceId(1L);

        when(abonentRepository.findByEmail(any(String.class))).thenReturn(java.util.Optional.of(abonent));
        when(serviceRepository.findByServiceId(1L)).thenReturn(java.util.Optional.of(s));

        when(serviceRepository.save(s)).thenReturn(s);
        when(abonentRepository.save(abonent)).thenReturn(abonent);

        service.unsubscribeAbonent(email, 1L);

        assertThat(s.getAbonentList(), hasSize(0));
    }
}
