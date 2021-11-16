package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.service.AbonentService;
import com.epam.springboot.homework1.core.service.model.Abonent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AbonentController {
    private final AbonentService abonentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent")
    public List<AbonentDto> getAllAbonents(){
        log.info("list all abonents in abonent controller");
        return abonentService.listAbonents();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent/{email}")
    public AbonentDto getAbonent(@PathVariable String email){
        log.info("get abonent {} in abonent controller", email);
        return abonentService.getAbonent(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/abonent")
    public AbonentDto createAbonent(@RequestBody AbonentDto abonentDto){
        log.info("create abonent in abonent controller");
        return abonentService.createAbonent(abonentDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/abonent/{email}")
    public AbonentDto updateAbonent(@PathVariable String email, @RequestBody AbonentDto abonentDto){
        log.info("upate abonent {} in abonent controller", abonentDto.getEmail());
        return abonentService.updateAbonent(email, abonentDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/abonent/{email}")
    public void deleteAbonent(@PathVariable String email){
        log.info("delete abonent {} in abonent controller", email);
        abonentService.deleteAbonent(email);
    }
}
