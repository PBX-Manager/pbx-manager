package dev.afonsogarcia.pbxmanager.config

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ValidationConfiguration {

    @Bean
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator
}
