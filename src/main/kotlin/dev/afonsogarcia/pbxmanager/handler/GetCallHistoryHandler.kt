package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.service.CallHistoryService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class GetCallHistoryHandler(
    private val callHistoryService: CallHistoryService
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse =
        ok().bodyValueAndAwait(callHistoryService.getCallHistory())
}
