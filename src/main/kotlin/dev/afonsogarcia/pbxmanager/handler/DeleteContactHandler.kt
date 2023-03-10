package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.PhonebookService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class DeleteContactHandler (
    private val phonebookService: PhonebookService
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse =
        if (phonebookService.contactExists(request.pathVariable("id").toInt())) {
            ok().bodyValueAndAwait(phonebookService.deleteContact(request.pathVariable("id").toInt()))
        } else {
            ServerResponse.notFound().build().awaitSingle()
        }
}
