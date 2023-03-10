package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.service.TellowsCidService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class CidRouter (
    private val tellowsCidService: TellowsCidService
) {

    @Bean
    suspend fun cidRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            GET("/cid/{phoneNumber}") {
                ok().bodyValueAndAwait(tellowsCidService.getCallerId(it.pathVariable("phoneNumber")))
            }
            GET("/cid/") {
                ok().bodyValueAndAwait("Desconhecido")
            }
        }
}
