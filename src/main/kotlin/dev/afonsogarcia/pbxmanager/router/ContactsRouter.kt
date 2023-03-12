package dev.afonsogarcia.pbxmanager.router

import dev.afonsogarcia.pbxmanager.handler.CreateContactHandler
import dev.afonsogarcia.pbxmanager.handler.DeleteContactHandler
import dev.afonsogarcia.pbxmanager.handler.GetContactHandler
import dev.afonsogarcia.pbxmanager.handler.GetContactsHandler
import dev.afonsogarcia.pbxmanager.handler.UpdateContactHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class ContactsRouter(
    private val createContactHandler: CreateContactHandler,
    private val updateContactHandler: UpdateContactHandler,
    private val deleteContactHandler: DeleteContactHandler,
    private val getContactHandler: GetContactHandler,
    private val getContactsHandler: GetContactsHandler
) {

    @Bean
    suspend fun contactsRoutes(): RouterFunction<ServerResponse> =
        coRouter {
            "/api".nest {
                "/contacts".nest {
                    GET("/") {
                        getContactsHandler.handleRequest(it)
                    }
                    POST("/") {
                        createContactHandler.handleRequest(it)
                    }
                    GET("/{id}") {
                        getContactHandler.handleRequest(it)
                    }
                    PATCH("/{id}") {
                        updateContactHandler.handleRequest(it)
                    }
                    DELETE("/{id}") {
                        deleteContactHandler.handleRequest(it)
                    }
                }
            }
        }
}
