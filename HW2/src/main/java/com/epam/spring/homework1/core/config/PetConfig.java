package com.epam.spring.homework1.core.config;

import com.epam.spring.homework1.core.pet.Cheetah;
import com.epam.spring.homework1.core.pet.Spider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.homework1.core.pet"}, excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= Spider.class)})
public class PetConfig {

    @Bean
    @Primary
    public Cheetah getCheetah(){
        return new Cheetah();
    }

    @Qualifier("myCheetah")
    @Bean
    public Cheetah getCheetah2(){
        return new Cheetah();
    }
}
