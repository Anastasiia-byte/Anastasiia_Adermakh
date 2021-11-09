package com.epam.spring.homework1.core.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC {
    public BeanC(){
        System.out.println(this.getClass().getSimpleName());
    }
}
