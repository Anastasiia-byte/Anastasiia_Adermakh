package com.epam.spring.homework1.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.core.beans")
@Import(OtherConfig.class)
public class BeansConfig {
}
