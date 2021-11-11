package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@PropertySource("classpath:application.properties")
public class BeanB implements Validator {
    @Value("${beanB.name}")
    private String name;
    @Value("${beanB.value}")
    private int value;

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


    private void customInitMethod(){
        System.out.println("Inside customInitMethod() beanB");
    }

    private void customDestroyMethod(){
        System.out.println("Inside customDestroyMethod() beanB");
    }

    private void otherInitMethod(){
        System.out.println("Inside otherInitMethod() beanB");
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanB did not pass the validation :(");
        }
    }
}
