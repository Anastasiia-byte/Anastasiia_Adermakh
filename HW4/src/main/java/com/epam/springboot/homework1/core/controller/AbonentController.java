package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.AbonentDto;
import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.service.AbonentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/abonent/{email}/service/{id}")
    public ServiceDto subscribeAbonentToService(@PathVariable String email, @PathVariable int id){
        log.info("subscribe abonent {} to service {}", email, id);
        return abonentService.subscribeAbonent(email, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/abonent/{email}/service/{id}")
    public AbonentDto unsubscribeAbonent(@PathVariable String email, @PathVariable int id){
        log.info("unsubscribe abonent {} from a service {}", email, id);
        return abonentService.unsubscribeAbonent(email, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/abonent")
    public AbonentDto createAbonent(@Validated @RequestBody AbonentDto abonentDto){
        log.info("create abonent in abonent controller");
        return abonentService.createAbonent(abonentDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/abonent/{email}")
    public AbonentDto updateAbonent(@PathVariable String email, @Validated @RequestBody AbonentDto abonentDto){
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
