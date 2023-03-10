package dev.afonsogarcia.pbxmanager.validation

import dev.afonsogarcia.pbxmanager.util.isPhoneNumber
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PhoneNumberValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention
annotation class PhoneNumber(
    val message: String,
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class PhoneNumberValidator : ConstraintValidator<PhoneNumber, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean =
        value.isNullOrEmpty() || value.isPhoneNumber()
}
