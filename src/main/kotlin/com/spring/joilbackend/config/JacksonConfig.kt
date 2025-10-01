package com.spring.joilbackend.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.spring.joilbackend.common.OptionModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JacksonConfig {

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(OptionModule())
        }
    }
}
