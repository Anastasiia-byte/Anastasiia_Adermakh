package com.epam.spring.homework1.core.pet;

import com.epam.spring.homework1.core.abstraction.Animal;
import org.springframework.stereotype.Component;

@Component
public class Spider implements Animal {
    @Override
    public String getAnimal() {
        return "Spider";
    }
}
