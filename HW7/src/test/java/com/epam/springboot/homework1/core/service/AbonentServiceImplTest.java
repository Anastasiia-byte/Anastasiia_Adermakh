package com.epam.springboot.homework1.core.service;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.model.Abonent;
import com.epam.springboot.homework1.core.repository.AbonentRepository;
import com.epam.springboot.homework1.core.service.impl.AbonentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AbonentServiceImplTest {
    @InjectMocks
    private AbonentServiceImpl abonentService;

    @Mock
    private AbonentRepository abonentRepository;

    private final static String MOCK_EMAIL = "email@email.com";

    @Test
    @DisplayName("Test findByEmail")
    void getAbonentTest() {
        Abonent expectedAbonent = new Abonent();
        expectedAbonent.setFirstName("Anastasiia");
        expectedAbonent.setLastName("Adermakh");
        expectedAbonent.setEmail(MOCK_EMAIL);
        when(abonentRepository.findByEmail(MOCK_EMAIL)).thenReturn(java.util.Optional.of(expectedAbonent));

        AbonentDto actualAbonent = abonentService.getAbonent(MOCK_EMAIL);

        assertEquals(expectedAbonent.getEmail(), actualAbonent.getEmail());
    }

    @Test
    @DisplayName("Test findAll")
    void listAbonentsTest(){
        when(abonentRepository.findAll()).thenReturn(Collections.singletonList(new Abonent()));

        List<AbonentDto> abonentDtoList = abonentService.listAbonents();

        assertThat(abonentDtoList, hasSize(1));
    }

    @Test
    @DisplayName("Test delete Abonent")
    void deleteAbonentTest(){
        doNothing().when(abonentRepository).deleteByEmail(MOCK_EMAIL);

        abonentService.deleteAbonent(MOCK_EMAIL);

        verify(abonentRepository, times(1)).deleteByEmail(MOCK_EMAIL);
    }

    @Test
    @DisplayName("Test delete Abonent with exception")
    void deleteWithExceptionTest(){
        doThrow(RuntimeException.class).when(abonentRepository).deleteByEmail(any(String.class));

        assertThrows(RuntimeException.class,
                () -> abonentService.deleteAbonent(MOCK_EMAIL));
    }

    @Test
    @DisplayName("Test updateByEmail Abonent")
    void updateAbonentTest(){
        Abonent abonent = new Abonent();
        abonent.setEmail(MOCK_EMAIL);
        abonent.setFirstName("Anastasiia");
        abonent.setLastName("Adermakh");
        abonent.setAbonentId(1L);

        Abonent abonentUpdated = new Abonent();
        abonentUpdated.setEmail(MOCK_EMAIL);
        abonentUpdated.setFirstName("Anastasiiaa");
        AbonentDto abonentDto = AbonentDto.builder()
                .email(MOCK_EMAIL)
                .firstName("Anastasiia")
                .lastName("Adermakh")
                .abonentId(1L)
                .build();

        doNothing().when(abonentRepository).updateById(abonent.getFirstName(), abonent.getLastName(), abonent.getAbonentId());

        abonentService.updateAbonent(MOCK_EMAIL, abonentDto);

        verify(abonentRepository, times(1)).updateById(abonent.getFirstName(),
                abonent.getLastName(), abonent.getAbonentId());
    }


}
