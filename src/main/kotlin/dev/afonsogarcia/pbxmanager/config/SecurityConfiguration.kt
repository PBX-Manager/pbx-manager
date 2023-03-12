package dev.afonsogarcia.pbxmanager.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import java.util.*

@ConfigurationProperties("security")
data class SecurityProperties(
    val allowedOrigins: List<String>
)

@Configuration
class SecurityConfiguration (
    private val securityProperties: SecurityProperties
) {
    @Bean
    fun corsWebFilter(): CorsWebFilter? {
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = securityProperties.allowedOrigins
        corsConfig.allowedMethods = listOf("OPTIONS", "GET", "POST", "PATCH", "DELETE")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsWebFilter(source)
    }
}