package com.epam.spring.homework2.core;

import com.epam.spring.homework2.core.beans.*;
import com.epam.spring.homework2.core.config.SecondConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SecondConfig.class);
        String[] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames){
            System.out.println(context.getBean(name));
        }

        context.close();
    }
}
