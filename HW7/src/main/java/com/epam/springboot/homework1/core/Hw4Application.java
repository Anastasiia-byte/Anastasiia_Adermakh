package com.epam.springboot.homework1.core;

import com.epam.springboot.homework1.core.repository.AbonentRepository;
import com.epam.springboot.homework1.core.repository.ServiceRepository;
import com.epam.springboot.homework1.core.repository.TariffRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = {AbonentRepository.class, ServiceRepository.class, TariffRepository.class})

public class Hw4Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw4Application.class, args);
    }

}
