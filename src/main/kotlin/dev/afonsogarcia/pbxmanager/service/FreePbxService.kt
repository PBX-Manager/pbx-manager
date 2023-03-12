package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.dto.freepbx.FetchAllExtensions
import dev.afonsogarcia.pbxmanager.dto.freepbx.FetchExtension
import dev.afonsogarcia.pbxmanager.model.Contact
import graphql.kickstart.spring.webclient.boot.GraphQLRequest
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest

@Service
class FreePbxService(
    private val graphQLWebClient: GraphQLWebClient
) {

    suspend fun getExtensionDetails(extensionNumber: String): FetchExtension? = try {
        val request = GraphQLRequest.builder().query(
            """
            {
              fetchExtension(extensionId: "$extensionNumber") {
                status
                extensionId
                user {
                  name
                }
              }
            }
        """.trimIndent()
        ).build()
        val response = graphQLWebClient.post(request).awaitSingle()
        if (!response.errors.isEmpty()) {
            null
        } else {
            response.get("fetchExtension", FetchExtension::class.java)
        }
    } catch (exception: BadRequest) {
        null
    }

    suspend fun getFreePbxContacts() =
        getAllExtensionDetails().extension?.map {
            Contact(
                id = -(it.extensionId?.toInt() ?: 0),
                name = it.user?.name ?: "",
                internalExtension = it.extensionId
            )
        } ?: emptyList()

    private suspend fun getAllExtensionDetails(): FetchAllExtensions {
        val request = GraphQLRequest.builder().query(
            """
            {
              fetchAllValidExtensions {
                status
                totalCount
                extension {
                  extensionId
                  user {
                    name
                  }
                }
              }
            }
        """.trimIndent()
        ).build()
        val response = graphQLWebClient.post(request).awaitSingle()
        return response.get("fetchAllValidExtensions", FetchAllExtensions::class.java)
    }
}