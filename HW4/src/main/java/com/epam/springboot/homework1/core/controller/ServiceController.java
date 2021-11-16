package com.epam.springboot.homework1.core.controller;

import com.epam.springboot.homework1.core.controller.dto.ServiceDto;
import com.epam.springboot.homework1.core.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ServiceController {
    private final ServiceService serviceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent/{email}/service")
    public List<ServiceDto> getAllServices(@PathVariable String email){
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
    public ServiceDto getService(@PathVariable int id){
        log.info("get service with id {} in service controller", id);
        return serviceService.getService(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/abonent/{email}/service/{id}")
    public ServiceDto getService(@PathVariable String email, @PathVariable int id){
        log.info("get abonent {}'s service with id {} in service controller",email, id);
        return serviceService.getService(email, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/abonent/{email}/service")
    public ServiceDto createService(@PathVariable String email, @RequestBody ServiceDto serviceDto){
        log.info("create service with id {}", serviceDto.getId());
        return serviceService.createService(email, serviceDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/service/{id}")
    public ServiceDto updateService(@PathVariable int id, @RequestBody ServiceDto serviceDto){
        log.info("update service with id {} int service controller", serviceDto.getId());
        return serviceService.updateService(id, serviceDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/service/{id}")
    public void deleteService(@PathVariable int id){
        log.info("delete service with id {}", id);
        serviceService.deleteService(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/abonent/{email}/service/{id}")
    public void deleteService(@PathVariable String email, @PathVariable int id){
        log.info("delete service with id {} from abonent {}'s service list", id, email);
        serviceService.deleteService(email, id);
    }
}
