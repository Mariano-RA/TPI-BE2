package com.dh.clinica;

import com.dh.clinica.dtos.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.registerModule(new JavaTimeModule());
    }

    @Bean
    public Mapper mappers(){
        Mapper mapper = new Mapper();
        return mapper;
    }

}
