package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.ContactsService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class DeleteContactHandler (
    private val contactsService: ContactsService
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse =
        if (contactsService.contactExists(request.pathVariable("id").toInt())) {
            ok().bodyValueAndAwait(contactsService.deleteContact(request.pathVariable("id").toInt()))
        } else {
            ServerResponse.notFound().build().awaitSingle()
        }
}
