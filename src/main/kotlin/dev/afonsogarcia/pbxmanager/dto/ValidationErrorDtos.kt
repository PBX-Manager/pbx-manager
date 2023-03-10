package dev.afonsogarcia.pbxmanager.dto

data class ValidationErrorsDto (
    val errors: List<ValidationErrorDto>
)
data class ValidationErrorDto (
    val fieldName: String,
    val message: String
)
