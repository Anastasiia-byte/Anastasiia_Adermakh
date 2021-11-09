package com.epam.spring.homework1.core.pet;

import com.epam.spring.homework1.core.abstraction.Animal;
import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {
    @Override
    public String getAnimal() {
        return "Cat";
    }
}
