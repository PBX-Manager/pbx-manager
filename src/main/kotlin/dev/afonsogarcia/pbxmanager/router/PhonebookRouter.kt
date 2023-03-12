package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.handler.*
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class PhonebookRouter(
    private val getPhonebookHandler: GetPhonebookHandler,
    private val syncPhonebookHandler: SyncPhonebookHandler
) {

    @Bean
    suspend fun phonebookRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            "/api".nest {
                "/phonebook".nest {
                    GET("/") {
                        getPhonebookHandler.handleRequest(it)
                    }
                    GET("/sync/") {
                        syncPhonebookHandler.handleRequest(it)
                    }
                }
            }
        }
}
