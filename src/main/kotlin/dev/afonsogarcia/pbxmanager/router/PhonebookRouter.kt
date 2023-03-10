package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.handler.CreateContactHandler
import dev.afonsogarcia.pbxmanager.handler.DeleteContactHandler
import dev.afonsogarcia.pbxmanager.handler.GetContactHandler
import dev.afonsogarcia.pbxmanager.handler.GetContactsHandler
import dev.afonsogarcia.pbxmanager.handler.GetPhonebookHandler
import dev.afonsogarcia.pbxmanager.handler.UpdateContactHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class PhonebookRouter(
    private val getPhonebookHandler: GetPhonebookHandler,
    private val createContactHandler: CreateContactHandler,
    private val updateContactHandler: UpdateContactHandler,
    private val deleteContactHandler: DeleteContactHandler,
    private val getContactHandler: GetContactHandler,
    private val getContactsHandler: GetContactsHandler
) {

    @Bean
    suspend fun phonebookRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            GET("/phonebook/") {
                getPhonebookHandler.handleRequest(it)
            }
            GET("/contacts/") {
                getContactsHandler.handleRequest(it)
            }
            POST("/contacts/") {
                createContactHandler.handleRequest(it)
            }
            GET("/contacts/{id}") {
                getContactHandler.handleRequest(it)
            }
            PATCH("/contacts/{id}") {
                updateContactHandler.handleRequest(it)
            }
            DELETE("/contacts/{id}") {
                deleteContactHandler.handleRequest(it)
            }
        }
}
