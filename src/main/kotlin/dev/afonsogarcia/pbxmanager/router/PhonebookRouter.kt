package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.service.PhonebookService
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class PhonebookRouter (
    private val phonebookService: PhonebookService
) {

    @Bean
    suspend fun phonebookRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            GET("/phonebook/") {
                ok().contentType(MediaType.TEXT_XML).bodyValueAndAwait(phonebookService.getPhonebook())
            }
            POST("/phonebook/contact/") {
                ok().bodyValueAndAwait(phonebookService.createContact(it.awaitBody()))
            }
            PATCH("/phonebook/contact/") {
                ok().bodyValueAndAwait(phonebookService.saveContact(it.awaitBody()))
            }
        }
}
