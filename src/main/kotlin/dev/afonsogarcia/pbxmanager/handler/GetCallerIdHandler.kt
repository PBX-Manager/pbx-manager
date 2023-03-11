package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.CallHistoryService
import dev.afonsogarcia.pbxmanager.service.TellowsCidService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class GetCallerIdHandler(
    private val tellowsCidService: TellowsCidService,
    private val callHistoryService: CallHistoryService
) {
    suspend fun handleRequest(request: ServerRequest, storeRecord: Boolean = true): ServerResponse =
        if (request.pathVariables().containsKey("phoneNumber")) {
            val number = request.pathVariable("phoneNumber")
            val name = tellowsCidService.getCallerId(number)
            if (storeRecord) {
                callHistoryService.registerCallHistory(name, number)
            }
            ok().bodyValueAndAwait(name)
        } else {
            if (storeRecord) {
                callHistoryService.registerCallHistory("Desconhecido")
            }
            ok().bodyValueAndAwait("Desconhecido")
        }
}
