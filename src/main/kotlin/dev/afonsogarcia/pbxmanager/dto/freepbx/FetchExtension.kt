package dev.afonsogarcia.pbxmanager.dto.freepbx

data class FetchExtension (
    val status: Boolean,
    val extensionId: String,
    val user: User
)