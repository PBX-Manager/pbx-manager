package dev.afonsogarcia.pbxmanager.model

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
    @NotNull val callTime: LocalDateTime = LocalDateTime.now()
)
