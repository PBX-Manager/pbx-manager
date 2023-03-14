package dev.afonsogarcia.pbxmanager.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
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
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.authorizeExchange {
            it.anyExchange().permitAll()
        }.cors {
            it.configurationSource {
                CorsConfiguration().apply {
                    allowedOrigins = securityProperties.allowedOrigins
                    allowedMethods = listOf("OPTIONS", "GET", "POST", "PATCH", "DELETE")
                }
            }
            it.disable()
        }.csrf {
            it.disable()
        }.build()
}