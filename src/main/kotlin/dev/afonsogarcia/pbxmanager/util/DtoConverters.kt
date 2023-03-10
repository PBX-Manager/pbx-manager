package dev.afonsogarcia.pbxmanager.util

import dev.afonsogarcia.pbxmanager.dto.DirectoryEntry
import dev.afonsogarcia.pbxmanager.dto.Telephone
import dev.afonsogarcia.pbxmanager.model.Contact

fun Contact.toDirectoryEntry() : DirectoryEntry {
    val directoryEntry = DirectoryEntry()

    directoryEntry.name = this.name

    directoryEntry.telephone = listOfNotNull(
        this.internalExtension?.toTelephone("Internal Extension"),
        this.homeNumber?.toTelephone("Home"),
        this.mobileNumber?.toTelephone("Mobile"),
        this.officeNumber?.toTelephone("Office"),
        this.officeMobileNumber?.toTelephone("Office Mobile"),
        this.otherNumber?.toTelephone("Other Number")
    )

    return directoryEntry
}

private fun String.toTelephone(label: String) = Telephone(
    label = label,
    phoneNumber = this
)
