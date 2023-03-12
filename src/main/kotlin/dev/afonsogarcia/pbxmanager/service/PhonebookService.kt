package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.dto.DirectoryEntry
import dev.afonsogarcia.pbxmanager.dto.PhoneDirectory
import dev.afonsogarcia.pbxmanager.dto.Telephone
import dev.afonsogarcia.pbxmanager.model.Contact
import org.springframework.stereotype.Service

@Service
class PhonebookService(
    private val contactsService: ContactsService,
    private val freePbxService: FreePbxService
) {

    suspend fun getPhonebook(): PhoneDirectory {
        val contacts = contactsService.getContacts()
        val freePbxContacts =
            freePbxService.getFreePbxContacts().filter { fpbx -> contacts.none { c -> c.internalExtension == fpbx.internalExtension } }
        val directoryEntries = (contacts + freePbxContacts).mapNotNull { it.toDirectoryEntry() }.sortedBy { it.name }

        return PhoneDirectory(directoryEntries)
    }

    private suspend fun Contact.toDirectoryEntry(): DirectoryEntry? {
        val directoryEntry = DirectoryEntry()
        val (name, internalExtension) = if (this.internalExtension != null) {
            val extensionData = freePbxService.getExtensionDetails(this.internalExtension)
            Pair(extensionData?.user?.name ?: this.name, extensionData?.extensionId)
        } else {
            Pair(this.name, null)
        }

        directoryEntry.name = name

        directoryEntry.telephone = listOfNotNull(
            internalExtension?.toTelephone("Internal Extension"),
            this.homeNumber?.toTelephone("Home"),
            this.mobileNumber?.toTelephone("Mobile"),
            this.officeNumber?.toTelephone("Office"),
            this.officeMobileNumber?.toTelephone("Office Mobile"),
            this.otherNumber?.toTelephone("Other Number")
        )

        return if (directoryEntry.telephone.isEmpty()) {
            null
        } else {
            directoryEntry
        }
    }

    private fun String.toTelephone(label: String) = Telephone(
        label = label,
        phoneNumber = this
    )
}
