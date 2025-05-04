package com.__days_of_code.social.media.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * Creates and provides a ModelMapper bean for mapping objects such as mapping DTOs to entities and vice versa.
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
