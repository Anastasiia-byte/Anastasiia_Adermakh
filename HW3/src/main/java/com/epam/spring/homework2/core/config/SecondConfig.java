package com.epam.spring.homework2.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FirstConfig.class)
public class SecondConfig {
}
