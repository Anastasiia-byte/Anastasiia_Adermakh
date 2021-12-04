package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.TariffDto;
import com.epam.springboot.homework1.core.service.TariffService;
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
public class TariffController {
    private final TariffService tariffService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{id}/tariff")
    public List<TariffDto> getAllTariffs(@PathVariable Long id){
        log.info("list all tariffs in controller");
        return tariffService.listTariffs(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tariff/{tariffId}")
    public TariffDto getTariff(@PathVariable Long tariffId){
        log.info("get tariff with id {} in controller", tariffId);
        return tariffService.getTariff(tariffId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{id}/tariff/price")
    public List<TariffDto> sortTariffsByPrice(@PathVariable Long id){
        log.info("sort tariff list by price in controller");
        return tariffService.sortByPrice(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{id}/tariff/name")
    public List<TariffDto> sortTariffsByNameStraight(@PathVariable Long id){
        log.info("sort tariff list by name alphabetically in controller");
        return tariffService.sortByNameStraight(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{id}/tariff/eman")
    public List<TariffDto> sortTariffsByNameOpposite(@PathVariable Long id){
        log.info("sort tariff list by name non-alphabetically in controller");
        return tariffService.sortByNameOpposite(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/service/{id}/tariff")
    public TariffDto createTariff(@PathVariable Long id, @Validated @RequestBody TariffDto tariffDto){
        log.info("create new tariff with id {}", tariffDto.getTariffId());
        return tariffService.createTariff(id, tariffDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/service/{id}/tariff/{tariffId}")
    public TariffDto updateTariff(@PathVariable Long id, @PathVariable Long tariffId,
                                  @Validated @RequestBody TariffDto tariffDto){
        log.info("update tariff with id {} in controller", tariffId);
        return tariffService.updateTariff(id, tariffId, tariffDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/tariff/{tariffId}")
    public void deleteTariff(@PathVariable Long tariffId){
        log.info("delete tariff with id {}", tariffId);
        tariffService.deleteTariff(tariffId);
    }
}
