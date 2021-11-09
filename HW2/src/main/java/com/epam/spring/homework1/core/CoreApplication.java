package com.epam.spring.homework1.core;

import com.epam.spring.homework1.core.config.BeansConfig;
import com.epam.spring.homework1.core.pet.Pet;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
		context.getBean(Pet.class).printPets();
	}

}
