package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.ContactsService
import dev.afonsogarcia.pbxmanager.service.PhonebookService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class GetPhonebookHandler (
    private val phonebookService: PhonebookService
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse =
        ok().contentType(MediaType.TEXT_XML).bodyValueAndAwait(phonebookService.getPhonebook())
}
