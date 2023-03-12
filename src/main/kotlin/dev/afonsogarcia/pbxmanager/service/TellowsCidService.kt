package dev.afonsogarcia.pbxmanager.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.afonsogarcia.pbxmanager.config.TellowsConfiguration
import dev.afonsogarcia.pbxmanager.dto.TellowsResponse
import dev.afonsogarcia.pbxmanager.util.isPhoneNumber
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Service
class TellowsCidService (
    private val config: TellowsConfiguration,
    private val objectMapper: ObjectMapper
) {

    private val client = WebClient.create(config.apiUrl);

    suspend fun getCallerId(phoneNumber: String): String = if (phoneNumber.isPhoneNumber()) {
        client.get().uri {
            it.pathSegment("num", phoneNumber)
                .queryParam("apikeyMd5", config.apiKeyMd5)
                .queryParam("country", config.country)
                .queryParam("lang", config.language)
                .queryParam("json", "1")
                .queryParam("showcallername", "1")
                .queryParam("numberformatinternational", "1")
                .build()
        }.accept(MediaType.ALL).awaitExchange {
            objectMapper.readValue(it.awaitBody(String::class), TellowsResponse::class.java)
        }.tellows.callerNames?.caller?.get(0) ?: phoneNumber
    } else {
        phoneNumber
    }
}
