package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@PropertySource("classpath:application.properties")
public class BeanD implements Validator {
    @Value("${beanD.name}")
    private String name;
    @Value("${beanD.value}")
    private int value;

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void customInitMethod(){
        System.out.println("Inside customInitMethod() beanD");
    }

    private void customDestroyMethod(){
        System.out.println("Inside customDestroyMethod() beanD");
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanD did not pass the validation :(");
        }
    }
}
