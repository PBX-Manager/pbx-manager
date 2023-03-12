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
            getFreePbxContacts().filter { fpbx -> contacts.none { c -> c.internalExtension == fpbx.internalExtension } }
        val directoryEntries = (contacts + freePbxContacts).mapNotNull { it.toDirectoryEntry() }.sortedBy { it.name }

        return PhoneDirectory(directoryEntries)
    }

    suspend fun syncPhonebook() {
        val contacts = contactsService.getContacts()

        for (contact in contacts) {
            if (contact.internalExtension != null) {
                val freePbxContact = freePbxService.getExtensionDetails(contact.internalExtension)
                if (freePbxContact != null) {
                    contactsService.saveContact(contact.withName(freePbxContact.user.name!!))
                } else {
                    contactsService.saveContact(contact.excludeInternalExtension())
                }
            }
        }

        val freePbxContacts =
            getFreePbxContacts().filter { fpbx -> contacts.none { c -> c.internalExtension == fpbx.internalExtension } }
        for (freePbxContact in freePbxContacts) {
            contactsService.saveContact(freePbxContact.removeId())
        }
    }

    private suspend fun getFreePbxContacts() =
        freePbxService.getAllExtensionDetails().extension?.map {
            Contact(
                id = -(it.extensionId?.toInt() ?: 0),
                name = it.user?.name ?: "",
                internalExtension = it.extensionId
            )
        } ?: emptyList()

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

    private fun Contact.withName(name: String): Contact =
        Contact(
            id = this.id,
            name = name,
            internalExtension = this.internalExtension,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber
        )

    private fun Contact.excludeInternalExtension(): Contact =
        Contact(
            id = this.id,
            name = this.name,
            internalExtension = null,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber
        )

    private fun Contact.removeId(): Contact =
        Contact(
            id = null,
            name = this.name,
            internalExtension = this.internalExtension,
            homeNumber = this.homeNumber,
            mobileNumber = this.mobileNumber,
            officeNumber = this.officeNumber,
            officeMobileNumber = this.officeMobileNumber,
            otherNumber = this.otherNumber
        )
}
