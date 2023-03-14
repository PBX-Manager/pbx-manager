package dev.afonsogarcia.pbxmanager.config

import org.hibernate.validator.constraints.URL
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("tellows")
data class TellowsConfiguration(
    @URL val apiUrl: String,
    val apiKeyMd5: String,
    val country: String,
    val language: String
)
