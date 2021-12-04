package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.exception.EntityNotFoundException;
import com.epam.springboot.homework1.core.service.AbonentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = AbonentController.class)
@AutoConfigureMockMvc
public class AbonentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbonentService abonentService;

    private static final String MOCK_EMAIL = "email@email.com";

    @Test
    @DisplayName("Test getAbonent")
    void getAbonentTest() throws Exception{
        AbonentDto abonentDto = AbonentDto.builder()
                .firstName("Anastasiia")
                .lastName("Adermakh")
                .email(MOCK_EMAIL)
                .build();

        when(abonentService.getAbonent(MOCK_EMAIL)).thenReturn(abonentDto);

        mockMvc.perform(get("/abonent/{email}", MOCK_EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(abonentDto.getFirstName()));
    }

    @Test
    @DisplayName("Test getAbonent with NotFoundException")
    void getAbonent_WithException_Test() throws Exception{
        String message = "Abonent " + MOCK_EMAIL + " was not found";
        when(abonentService.getAbonent(MOCK_EMAIL)).thenThrow(new EntityNotFoundException(message));

        mockMvc.perform(get("/abonent/{email}", MOCK_EMAIL))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test listAll Abonents")
    void listAllTest() throws Exception{
        AbonentDto abonentDto = AbonentDto
                .builder()
                .firstName("TESTNAME")
                .build();

        when(abonentService.listAbonents()).thenReturn(Collections.singletonList(abonentDto));

        mockMvc.perform(get("/abonent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value(abonentDto.getFirstName()));
    }

    @Test
    @DisplayName("Test createAbonent")
    void createAbonentTest() throws Exception {
        AbonentDto abonentDto = AbonentDto
                .builder()
                .firstName("Anastasiia")
                .lastName("Adermakh")
                .email(MOCK_EMAIL)
                .build();

        when(abonentService.createAbonent(abonentDto)).thenReturn(abonentDto);

        mockMvc.perform(post("/abonent")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(abonentDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(abonentDto.getFirstName()));
    }

    @Test
    @DisplayName("Test updateAbonent")
    void updateAbonentTest() throws Exception{
        AbonentDto abonentDto = AbonentDto
                .builder()
                .firstName("Anastasiia")
                .lastName("Adermakh")
                .email(MOCK_EMAIL)
                .build();

        AbonentDto abonentUpdated = AbonentDto
                .builder()
                .firstName("Anastasiiaaaa")
                .lastName("Adermakh")
                .email(MOCK_EMAIL)
                .build();

        when(abonentService.updateAbonent(MOCK_EMAIL, abonentDto)).thenReturn(abonentUpdated);

        mockMvc.perform(put("/abonent/{email}", MOCK_EMAIL)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(abonentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(abonentUpdated.getFirstName()));

    }

    @Test
    @DisplayName("Test createInvalidAbonent")
    void createInvalidAbonentTest() throws Exception{
        mockMvc.perform(post("/abonent")
                        .contentType("application/json")
                        .content("{}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Test emailValidation")
    void validateEmailTest(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        AbonentDto abonentDto = AbonentDto.builder()
                .email("")
                .build();

        Set<ConstraintViolation<AbonentDto>> violations = validator.validate(abonentDto);

        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Test deleteAbonent")
    void deleteAbonentTest() throws Exception{
        doNothing().when(abonentService).deleteAbonent(MOCK_EMAIL);

        mockMvc.perform(delete("/abonent/{email}", MOCK_EMAIL))
                .andExpect(status().isNoContent());

        verify(abonentService, times(1)).deleteAbonent(MOCK_EMAIL);
    }
}
