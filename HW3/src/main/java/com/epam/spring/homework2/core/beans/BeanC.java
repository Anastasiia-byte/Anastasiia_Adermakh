package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(3)
@PropertySource("classpath:application.properties")
public class BeanC implements Validator {
    @Value("${beanC.name}")
    private String name;
    @Value("${beanC.value}")
    private int value;

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void customInitMethod(){
        System.out.println("Inside customInitMethod() beanC");
    }

    private void customDestroyMethod(){
        System.out.println("Inside customDestroyMethod() beanC");
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanC did not pass the validation :(");
        }
    }
}
