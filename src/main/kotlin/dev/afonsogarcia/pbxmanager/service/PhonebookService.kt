package dev.afonsogarcia.pbxmanager.service

import dev.afonsogarcia.pbxmanager.dto.PhoneDirectory
import dev.afonsogarcia.pbxmanager.model.Contact
import dev.afonsogarcia.pbxmanager.repository.ContactRepository
import dev.afonsogarcia.pbxmanager.util.toDirectoryEntry
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class PhonebookService(
    private val contactRepository: ContactRepository
) {

    suspend fun getContacts(): List<Contact> = contactRepository.findAll().toList()

    suspend fun getPhonebook(): PhoneDirectory = PhoneDirectory(getContacts().map {
        it.toDirectoryEntry()
    })

    suspend fun saveContact(contact: Contact): Contact = contactRepository.save(contact)

    suspend fun contactExists(contactId: Int): Boolean = contactRepository.existsById(contactId)

    suspend fun deleteContact(contactId: Int): Unit = contactRepository.deleteById(contactId)

    suspend fun getContact(contactId: Int): Contact? = contactRepository.findById(contactId)
}
