package dev.afonsogarcia.pbxmanager.config

import dev.afonsogarcia.pbxmanager.dto.*
import dev.afonsogarcia.pbxmanager.dto.freepbx.*
import jakarta.validation.constraints.Pattern
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints

@Configuration
@RegisterReflectionForBinding(classes = [
    User::class,
    CoreDevice::class,
    FetchAllExtensions::class,
    Extension::class,
    FetchExtension::class,
    PhoneDirectory::class,
    DirectoryEntry::class,
    Telephone::class,
    TellowsResponse::class,
    Tellows::class,
    CallerTypes::class,
    CallerType::class,
    CallerNames::class,
    ValidationErrorsDto::class,
    ValidationErrorDto::class
])
@ImportRuntimeHints(NativeHints::class)
class NativeConfiguration

class NativeHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.reflection().registerType(Pattern.Flag::class.java)
    }

}