package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE implements Validator {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Inside postConstruct() method beanE");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Inside preDestroy() method beanE");
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanE did not pass the validation :(");
        }
    }
}
