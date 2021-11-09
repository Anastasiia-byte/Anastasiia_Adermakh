package com.epam.spring.homework1.core;

import com.epam.spring.homework1.core.config.BeansConfig;
import com.epam.spring.homework1.core.pet.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
		context.getBean(Pet.class).printPets();
	}

}
