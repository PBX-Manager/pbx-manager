package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.model.Contact
import dev.afonsogarcia.pbxmanager.service.PhonebookService
import jakarta.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class UpdateContactHandler(
    validator: Validator,
    private val phonebookService: PhonebookService
) : ValidationHandler<Contact, Validator>(Contact::class, validator) {
    override suspend fun processBody(validBody: Contact, request: ServerRequest): ServerResponse =
        if (phonebookService.contactExists(request.pathVariable("id").toInt())) {
            ok().bodyValueAndAwait(phonebookService.saveContact(validBody))
        } else {
            notFound().build().awaitSingle()
        }
}
