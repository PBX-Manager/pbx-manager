package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.model.Contact
import dev.afonsogarcia.pbxmanager.repository.ContactRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class ContactsService(
    private val contactRepository: ContactRepository,
    private val freePbxService: FreePbxService
) {

    suspend fun getContacts(): List<Contact> = contactRepository.findAll().toList()

    suspend fun saveContact(contact: Contact): Contact = contactRepository.save(contact)

    suspend fun contactExists(contactId: Int): Boolean = contactRepository.existsById(contactId)

    suspend fun deleteContact(contactId: Int): Unit = contactRepository.deleteById(contactId)

    suspend fun getContact(contactId: Int): Contact? = contactRepository.findById(contactId)

    suspend fun syncPhonebook() {
        val contacts = contactRepository.findAllByInternalExtensionNotNull().toList()

        for (contact in contacts) {
            if (contact.internalExtension != null) {
                val freePbxContact = freePbxService.getExtensionDetails(contact.internalExtension)
                if (freePbxContact != null) {
                    saveContact(contact.withName(freePbxContact.user.name!!))
                } else {
                    saveContact(contact.excludeInternalExtension())
                }
            }
        }

        val freePbxContacts =
            freePbxService.getFreePbxContacts().filter { fpbx -> contacts.none { c -> c.internalExtension == fpbx.internalExtension } }
        for (freePbxContact in freePbxContacts) {
            saveContact(freePbxContact.removeId())
        }
    }
}
