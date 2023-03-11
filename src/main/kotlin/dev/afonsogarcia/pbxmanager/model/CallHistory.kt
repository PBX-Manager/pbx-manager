package dev.afonsogarcia.pbxmanager.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import dev.afonsogarcia.pbxmanager.serializer.TimestampDeserializer
import dev.afonsogarcia.pbxmanager.validation.PhoneNumber
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class CallHistory (
    @Id val id: Int? = null,
    @NotNull val name: String,
    @PhoneNumber("Not a valid phone number") val number: String? = null,
    @JsonSerialize(using = TimestampDeserializer::class) @NotNull val callTime: LocalDateTime = LocalDateTime.now()
)
