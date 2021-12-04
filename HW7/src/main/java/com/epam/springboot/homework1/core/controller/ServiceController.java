package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.mapper.ServiceMapper;
import com.epam.springboot.homework1.core.service.ServiceService;
import com.epam.springboot.homework1.core.model.Service;
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
public class ServiceController {
    private final ServiceService serviceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent/{email}/service")
    public List<ServiceDto> getAbonentsServices(@PathVariable String email){
        log.info("list all abonent {}' s services in service controller", email);
        return serviceService.listServices(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service")
    public List<ServiceDto> getAllServices(){
        log.info("list all services in service controller");
        return serviceService.listServices();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{id}")
    public ServiceDto getService(@PathVariable Long id){
        log.info("get service with id {} in service controller", id);
        return serviceService.getService(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent/{email}/service/{id}")
    public ServiceDto getService(@PathVariable String email, @PathVariable Long id){
        log.info("get abonent {}'s service with id {} in service controller",email, id);
        return serviceService.getService(email, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/service")
    public ServiceDto createService(@Validated @RequestBody ServiceDto serviceDto){
        log.info("create service with id {}", serviceDto.getServiceId());
        return serviceService.createService(serviceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/service/{id}")
    public ServiceDto updateService(@PathVariable Long id, @Validated @RequestBody ServiceDto serviceDto){
        log.info("update service with id {} int service controller", serviceDto.getServiceId());
        return serviceService.updateService(id, serviceDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/service/{id}")
    public void deleteService(@PathVariable Long id){
        log.info("delete service with id {}", id);
        serviceService.deleteService(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/abonent/{email}/service/{id}")
    public ServiceDto subscribeAbonent(@PathVariable String email, @PathVariable Long id){
        log.info("subscribe abonent {} to service {} in service controller", email, id);
        return serviceService.subscribeAbonentToService(email, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/abonent/{email}/service/{id}")
    public ServiceDto unsubscribeAbonent(@PathVariable String email, @PathVariable Long id){
        log.info("unsubscribe abonent {} to service {} in service controller", email, id);
        return serviceService.unsubscribeAbonent(email, id);
    }
}
