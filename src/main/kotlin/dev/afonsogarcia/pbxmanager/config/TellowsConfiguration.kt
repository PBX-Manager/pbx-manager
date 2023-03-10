package dev.afonsogarcia.pbxmanager.config

import org.hibernate.validator.constraints.URL
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import org.springframework.web.reactive.function.client.WebClient

@Validated
@ConfigurationProperties("tellows")
data class TellowsConfiguration(
    @URL val apiUrl: String,
    val apiKeyMd5: String,
    val country: String,
    val language: String
)

@Configuration
class TellowsBeans (
    private val config: TellowsConfiguration
) {

    @Bean
    fun tellowsWebClient(): WebClient = WebClient.create(config.apiUrl);
}
