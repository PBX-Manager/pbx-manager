package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.handler.GetCallHistoryHandler
import dev.afonsogarcia.pbxmanager.handler.GetCallerIdHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class CidRouter(
    private val getCallerIdHandler: GetCallerIdHandler,
    private val getCallHistoryHandler: GetCallHistoryHandler
) {

    @Bean
    fun cidRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            "/cid".nest {
                "/check".nest {
                    GET("/{phoneNumber}") {
                        getCallerIdHandler.handleRequest(it, false)
                    }
                    GET("/") {
                        getCallerIdHandler.handleRequest(it, false)
                    }
                }
                GET("/{phoneNumber}") {
                    getCallerIdHandler.handleRequest(it)
                }
                GET("/") {
                    getCallerIdHandler.handleRequest(it)
                }
            }
            "/call-history".nest {
                GET("/") {
                    getCallHistoryHandler.handleRequest(it)
                }
            }
        }
}
