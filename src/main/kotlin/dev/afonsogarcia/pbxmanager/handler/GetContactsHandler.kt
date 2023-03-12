package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.ContactsService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class GetContactsHandler (
    private val contactsService: ContactsService
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse =
        ok().bodyValueAndAwait(contactsService.getContacts().sortedBy { it.name })
}
