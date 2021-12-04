package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.model.Service;
import com.epam.springboot.homework1.core.model.Tariff;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.repository.TariffRepository;
import com.epam.springboot.homework1.core.service.impl.TariffServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TariffServiceImplTest {
    @InjectMocks
    private TariffServiceImpl tariffService;

    @Mock
    private TariffRepository tariffRepository;
    @Mock
    private ServiceRepository serviceRepository;

    private static Service mockService;

    @BeforeAll
    static void initService(){
        mockService = new Service();
        mockService.setServiceId(1L);
    }


    @Test
    @DisplayName("Test getById Tariff")
    void getTariffTest(){
        Tariff expectedTariff = new Tariff();
        expectedTariff.setTariffId(1L);
        expectedTariff.setName("tariff");

        when(tariffRepository.findByService_TariffList_TariffId(1L)).thenReturn(java.util.Optional.of(expectedTariff));

        TariffDto actualTariff = tariffService.getTariff(1L);

        assertEquals(expectedTariff.getName(), actualTariff.getName());
    }

    @Test
    @DisplayName("Test findAllByServiceId Tariff")
    void getAllTariffsTest(){
        when(tariffRepository.findAllByService(mockService)).thenReturn(Collections.singletonList(new Tariff()));
        when(serviceRepository.findByServiceId(1L)).thenReturn(Optional.of(mockService));

        List<TariffDto> tariffDtoList = tariffService.listTariffs(1L);

        assertThat(tariffDtoList, hasSize(1));
    }

    @Test
    @DisplayName("Test updateById Tariff")
    void updateTariffTest(){

        Tariff tariff = new Tariff();
        tariff.setTariffId(1L);
        tariff.setName("tariff");
        tariff.setPrice(100);

        TariffDto tariffDto = TariffDto.builder()
                .tariffId(1L)
                .name("tariff")
                .price(100L)
                .build();
        when(serviceRepository.findByServiceId(1L)).thenReturn(Optional.of(mockService));
        doNothing().when(tariffRepository).updateById(tariff.getName(), tariff.getPrice(), tariff.getTariffId());

        tariffService.updateTariff(mockService.getServiceId(), tariffDto.getTariffId(), tariffDto);

        verify(tariffRepository, times(1)).updateById(tariff.getName(), tariff.getPrice(), tariff.getTariffId());

    }

    @Test
    @DisplayName("Test deleteById Tariff")
    void deleteTariffTest(){
        Tariff tariff = new Tariff();
        tariff.setTariffId(1L);
        tariff.setName("tariff");
        tariff.setPrice(100);
        tariff.setService(mockService);

        when(tariffRepository.findByService_TariffList_TariffId(1L)).thenReturn(Optional.of(tariff));
        doNothing().when(tariffRepository).deleteByTariffId(1L);

        tariffService.deleteTariff(1L);

        verify(tariffRepository, times(1)).deleteByTariffId(1L);
    }

    @Test
    @DisplayName("Test deleteById with Exception Tariff")
    void deleteTariffWithExceptionTest(){
        Tariff tariff = new Tariff();
        tariff.setTariffId(1L);
        tariff.setName("tariff");
        tariff.setPrice(100);
        tariff.setService(mockService);

        when(tariffRepository.findByService_TariffList_TariffId(1L)).thenReturn(Optional.of(tariff));
        doThrow(RuntimeException.class).when(tariffRepository).deleteByTariffId(any(Long.class));

        assertThrows(RuntimeException.class,
                () -> tariffService.deleteTariff(1L));
    }

    @Test
    @DisplayName("Test sortByPrice Tariff")
    void sortTariffsByPriceTest(){
        Tariff tariff1 = new Tariff();
        tariff1.setTariffId(1L);
        tariff1.setName("tariff");
        tariff1.setPrice(100);
        tariff1.setService(mockService);

        Tariff tariff2 = new Tariff();
        tariff2.setTariffId(2L);
        tariff2.setName("tariff");
        tariff2.setPrice(1000);
        tariff2.setService(mockService);

        List<Tariff> expected = new ArrayList<>(Arrays.asList(tariff2, tariff1));

        when(serviceRepository.findByServiceId(1L)).thenReturn(Optional.of(mockService));
        when(tariffRepository.findAllByService(mockService, Sort.by(Sort.Direction.ASC, "price"))).thenReturn(expected);

        List<TariffDto> actual = tariffService.sortByPrice(mockService.getServiceId());

        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }
}
