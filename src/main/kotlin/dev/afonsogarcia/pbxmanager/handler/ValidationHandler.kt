package dev.afonsogarcia.pbxmanager.handler

import dev.afonsogarcia.pbxmanager.dto.ValidationErrorDto
import dev.afonsogarcia.pbxmanager.dto.ValidationErrorsDto
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validator
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import kotlin.reflect.KClass

abstract class ValidationHandler<T : Any, U : Validator>(
    private val validationClass: KClass<T>, private val validator: U
) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse {
        val body = request.awaitBody(validationClass)
        val errors = validator.validate(body)
        return if (errors.isEmpty()) {
            processBody(body, request)
        } else {
            onValidationErrors(errors)
        }
    }

    abstract suspend fun processBody(validBody: T, request: ServerRequest): ServerResponse

    private suspend fun onValidationErrors(errors: Set<ConstraintViolation<T>>): ServerResponse =
        badRequest().contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ValidationErrorsDto(errors.map {
                ValidationErrorDto(
                    it.propertyPath.toString(),
                    it.message
                )
            }))
}
