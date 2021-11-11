package com.epam.spring.homework2.core.config;

import com.epam.spring.homework2.core.beans.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan("com.epam.spring.homework2.core.beans")
public class FirstConfig {

    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"

    )
    public BeanB beanB(){
        return new BeanB();
    }

    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"
    )
    public BeanC beanC(){
        return new BeanC();
    }

    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"
    )
    public BeanD beanD(){
        return new BeanD();
    }

    @Bean
    @Lazy
    public BeanF beanF(){
        return new BeanF();
    }

}
