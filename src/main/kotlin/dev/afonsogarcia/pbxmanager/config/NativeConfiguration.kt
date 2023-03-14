package dev.afonsogarcia.pbxmanager.config

import dev.afonsogarcia.pbxmanager.dto.*
import dev.afonsogarcia.pbxmanager.dto.freepbx.*
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.context.annotation.Configuration

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
class NativeConfiguration