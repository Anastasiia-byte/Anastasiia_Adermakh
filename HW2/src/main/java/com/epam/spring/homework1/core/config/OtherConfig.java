package com.epam.spring.homework1.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.core.other")
@Import({PetConfig.class})
public class OtherConfig {
}
