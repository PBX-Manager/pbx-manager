package dev.afonsogarcia.pbxmanager.dto.freepbx

data class FetchAllExtensions (
    val status: Boolean?,
    val totalCount: Int?,
    val extension: List<Extension>?
)

data class Extension (
    val id: String?,
    val extensionId: String?,
    val user: User?,
    val coreDevice: CoreDevice?
)