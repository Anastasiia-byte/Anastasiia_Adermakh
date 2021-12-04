package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.service.TariffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TariffController.class)
@AutoConfigureMockMvc
public class TariffControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TariffService tariffService;

    private static TariffDto tariffDto;

    @BeforeAll
    static void createTariff(){
        tariffDto = TariffDto.builder()
                .tariffId(1L)
                .price(100L)
                .name("tariff")
                .build();
    }

    @Test
    @DisplayName("Test getTariff")
    void getTariffTest() throws Exception{

        when(tariffService.getTariff(1L)).thenReturn(tariffDto);

        mockMvc.perform(get("/tariff/{tariffId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(tariffDto.getName()));
    }

    @Test
    @DisplayName("Test getTariff with Exception")
    void getTariffTest_WithException() throws Exception{
        when(tariffService.getTariff(1L)).thenThrow(new EntityNotFoundException("tariff was not found"));

        mockMvc.perform(get("/tariff/{tariffId}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test getAllTariffs")
    void listAllTariffsTest() throws Exception{
        when(tariffService.listTariffs(1L)).thenReturn(Collections.singletonList(tariffDto));

        mockMvc.perform(get("/service/{id}/tariff", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(tariffDto.getName()));
    }

    @Test
    @DisplayName("Test createTariff")
    void createTariffTest() throws Exception {
        when(tariffService.createTariff(1L, tariffDto)).thenReturn(tariffDto);

        mockMvc.perform(post("/service/{id}/tariff", 1L)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(tariffDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(tariffDto.getName()));
    }

    @Test
    @DisplayName("Test createInvalidTariff")
    void createInvalidTariff() throws Exception{
        TariffDto invalidTariff = TariffDto.builder()
                .tariffId(1L)
                .price(0L)
                .name("")
                .build();

        mockMvc.perform(post("/service/{id}/tariff", 1L)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(invalidTariff)))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test deleteTariff")
    void deleteTariffTest() throws Exception{
        doNothing().when(tariffService).deleteTariff(1L);

        mockMvc.perform(delete("/tariff/{tariffId}", 1L))
                .andExpect(status().isNoContent());

        verify(tariffService, times(1)).deleteTariff(1L);
    }

    @Test
    @DisplayName("Test updateTariff")
    void updateTariffTest() throws Exception{
        TariffDto tariffUpdated = TariffDto.builder()
                .tariffId(1L)
                .name("tariffff")
                .price(100L)
                .build();

        when(tariffService.updateTariff(1L, 1L, tariffDto)).thenReturn(tariffUpdated);

        mockMvc.perform(MockMvcRequestBuilders.put("/service/{id}/tariff/{tariffId}", 1L, 1L)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(tariffDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariffUpdated.getName()));
    }

    @Test
    @DisplayName("Test sortByPriceServices")
    void sortServicesByPrice() throws Exception{
        TariffDto secondTariff = TariffDto.builder()
                .tariffId(2L)
                .price(200L)
                .name("tariff")
                .build();

        when(tariffService.sortByPrice(1L)).thenReturn(List.of(secondTariff, tariffDto));

        mockMvc.perform(get("/service/{id}/tariff/price", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].price").value(secondTariff.getPrice()));

    }

    @Test
    @DisplayName("Test sortByNameServices")
    void sortServicesByName() throws Exception{
        TariffDto secondTariff = TariffDto.builder()
                .tariffId(2L)
                .price(100L)
                .name("atariff")
                .build();

        when(tariffService.sortByNameStraight(1L)).thenReturn(List.of(secondTariff, tariffDto));

        mockMvc.perform(get("/service/{id}/tariff/name", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(secondTariff.getName()));

    }

    @Test
    @DisplayName("Test sortByNameOppositeServices")
    void sortServicesByNameOpposite() throws Exception{
        TariffDto secondTariff = TariffDto.builder()
                .tariffId(2L)
                .price(100L)
                .name("atariff")
                .build();

        when(tariffService.sortByNameOpposite(1L)).thenReturn(List.of(tariffDto, secondTariff));

        mockMvc.perform(get("/service/{id}/tariff/eman", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].name").value(secondTariff.getName()));

    }

}
