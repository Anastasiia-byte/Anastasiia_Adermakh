package com.epam.spring.homework2.core.beans;

import com.epam.spring.homework2.core.abstraction.Validator;
import org.springframework.stereotype.Component;

@Component
public class BeanF implements Validator {

    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        System.out.println("Inside Validate method");
        if(name == null || value < 0){
            System.out.println("BeanF did not pass the validation :(");
        }
    }
}
