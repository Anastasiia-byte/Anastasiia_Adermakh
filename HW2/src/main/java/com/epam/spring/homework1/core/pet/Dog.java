package com.epam.spring.homework1.core.pet;

import com.epam.spring.homework1.core.abstraction.Animal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Dog implements Animal {

    @Override
    public String getAnimal() {
        return "Dog";
    }
}
